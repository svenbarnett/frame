package com.deepblue777.frame.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置文件
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 14:54
 * @since 1.0
 */
@Configuration
public class ShiroConfig {

  private static final String CACHE_KEY = "shiro:cache:";
  private static final String SESSION_KEY = "shiro:session:";
  private static final String NAME = "custom.name";
  private static final String VALUE = "/";

  @Bean("shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager,
                                            @Qualifier("frameShiroSerivce") FrameShiroSerivce frameShiroSerivce) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    // 设置自定义的角色权限过滤器
    Map<String, Filter> filterMap = new LinkedHashMap<>(1);
    filterMap.put("roles", rolesAuthorizationFilter());
    shiroFilterFactoryBean.setFilters(filterMap);

    shiroFilterFactoryBean.setLoginUrl("/frame/login");
    shiroFilterFactoryBean.setSuccessUrl("/frame/index");
    shiroFilterFactoryBean.setUnauthorizedUrl("/frame/unauthorized");
    // 加载数据库的相关权限路由控制
    shiroFilterFactoryBean.setFilterChainDefinitionMap(frameShiroSerivce.loadFilterChainDefinitions());
    return shiroFilterFactoryBean;
  }

  @Bean("rolesAuthorizationFilter")
  public RolesAuthorizationFilter rolesAuthorizationFilter() {
    return new FrameRolesAuthorizationFilter();
  }

  @Bean("securityManager")
  public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(authRealm);
    return securityManager;
  }

  @Bean("authRealm")
  public AuthRealm authRealm() {
    AuthRealm authRealm = new AuthRealm();
    return authRealm;
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }

  /**
   * Redis集群使用RedisClusterManager，单个Redis使用RedisManager
   */

  @Bean("redisManager")
  public RedisManager redisManager(RedisProperties redisProperties) {
    RedisManager redisManager = new RedisManager();
    redisManager.setHost(redisProperties.getHost());
    redisManager.setPassword(redisProperties.getPassword());
    redisManager.setDatabase(redisProperties.getDatabase());
    redisManager.setTimeout(redisProperties.getTimeout().getNano());
    return redisManager;
  }

  @Bean("redisCacheManager")
  public RedisCacheManager redisCacheManager(@Qualifier("redisManager") RedisManager redisManager) {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    redisCacheManager.setRedisManager(redisManager);
    redisCacheManager.setExpire(86400);
    redisCacheManager.setKeyPrefix(CACHE_KEY);
    return redisCacheManager;
  }

  @Bean("redisSessionDAO")
  public RedisSessionDAO redisSessionDAO(@Qualifier("redisManager") RedisManager redisManager) {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setExpire(86400);
    redisSessionDAO.setKeyPrefix(SESSION_KEY);
    redisSessionDAO.setRedisManager(redisManager);
    return redisSessionDAO;
  }

  @Bean("sessionManager")
  public DefaultWebSessionManager sessionManager(@Qualifier("redisSessionDAO") RedisSessionDAO sessionDAO, @Qualifier("simpleCookie") SimpleCookie simpleCookie) {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    sessionManager.setSessionDAO(sessionDAO);
    sessionManager.setSessionIdCookieEnabled(true);
    sessionManager.setSessionIdCookie(simpleCookie);
    return sessionManager;
  }

  @Bean("simpleCookie")
  public SimpleCookie simpleCookie() {
    SimpleCookie simpleCookie = new SimpleCookie();
    simpleCookie.setName(NAME);
    simpleCookie.setValue(VALUE);
    return simpleCookie;
  }
}


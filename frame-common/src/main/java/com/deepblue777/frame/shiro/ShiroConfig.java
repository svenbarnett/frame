package com.deepblue777.frame.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro的配置文件
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 14:54
 * @since 1.0
 */
@Configuration
public class ShiroConfig {

  @Bean("shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    shiroFilterFactoryBean.setLoginUrl("/login");
    shiroFilterFactoryBean.setSuccessUrl("/index");
    shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

    LinkedHashMap<String, String> filterDefinitionMap = new LinkedHashMap<>();
    filterDefinitionMap.put("/index", "anon");
    filterDefinitionMap.put("/login", "anon");
    filterDefinitionMap.put("/statics/**", "anon");
    filterDefinitionMap.put("/**", "anon");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterDefinitionMap);

    return shiroFilterFactoryBean;
  }

  @Bean("securityManager")
  public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(authRealm);
    return securityManager;
  }

  @Bean("authRealm")
  public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
    AuthRealm authRealm = new AuthRealm();
    authRealm.setCredentialsMatcher(credentialsMatcher);
    return authRealm;
  }


  @Bean("credentialsMatcher")
  public CredentialsMatcher credentialsMatcher() {
    return new CredentialsMatcher();
  }


  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }
}


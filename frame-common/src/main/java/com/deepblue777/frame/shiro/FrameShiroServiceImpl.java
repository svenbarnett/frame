package com.deepblue777.frame.shiro;

import com.deepblue777.frame.domain.FramePermission;
import com.deepblue777.frame.domain.FrameRole;
import com.deepblue777.frame.service.FramePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * shiro服务类，获取权限和重新加载权限
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/18 11:00
 * @since 1.0
 */
@Service("frameShiroSerivce")
public class FrameShiroServiceImpl implements FrameShiroSerivce {

  private final static Logger LOGGER = LoggerFactory.getLogger(FrameShiroServiceImpl.class);

  @Autowired
  private FramePermissionService framePermissionService;

  @Override
  public Map<String, String> loadFilterChainDefinitions() {
    LinkedHashMap<String, String> filterDefinitionMap = new LinkedHashMap<>();

    List<FramePermission> permissions = framePermissionService.findAllPermissions();

    // 权限控制map.从数据库获取
    if (permissions.size() > 0) {
      for (FramePermission permission : permissions) {
        if (permission.getRoles().size() == 0) {
          continue;
        }
        Set<FrameRole> roles = permission.getRoles();
        List<String> roleList = new ArrayList<>();
        List<String> permList = new ArrayList<>();
        for (FrameRole role : roles) {
          roleList.add(role.getRoleName());
          permList.add(role.getRoleName() + ":" + permission.getPermissionName());
        }
        String joinRoles = StringUtils.join(roleList, ",");
        String joinPerms = StringUtils.join(permList, ",");
        filterDefinitionMap.put("/**/" + permission.getUri(), "authc,roles[" + joinRoles + "],perms[" + joinPerms + "]");
      }
    }
    filterDefinitionMap.put("/**/frame/login", "anon");
    filterDefinitionMap.put("/**/frame/user/doLogin", "anon");
    filterDefinitionMap.put("/**/statics/**", "anon");
    filterDefinitionMap.put("/**/front/**", "anon");
    filterDefinitionMap.put("/**/api/**", "anon");
    filterDefinitionMap.put("/**", "authc");
    // 获取数据库的权限
    LOGGER.debug("#### 获取数据库资源权限为：{}", filterDefinitionMap.toString());
    return filterDefinitionMap;
  }

  @Override
  public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean) {
    synchronized (this) {
      AbstractShiroFilter shiroFilter;
      try {
        shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
      } catch (Exception e) {
        throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
      }

      PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
      DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

      // 清空老的权限控制
      manager.getFilterChains().clear();

      shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
      shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
      // 重新构建生成
      Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
      for (Map.Entry<String, String> entry : chains.entrySet()) {
        String url = entry.getKey();
        String chainDefinition = entry.getValue().trim()
                .replace(" ", "");
        manager.createChain(url, chainDefinition);
      }
    }
  }
}

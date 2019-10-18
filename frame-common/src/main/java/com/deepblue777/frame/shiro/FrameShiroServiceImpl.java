package com.deepblue777.frame.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import java.util.Map;

/**
 * shiro服务类，获取权限和重新加载权限
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/18 11:00
 * @since 1.0
 */
public class FrameShiroServiceImpl implements FrameShiroSerivce {
  @Override
  public Map<String, String> loadFilterChainDefinitions() {
    return null;
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

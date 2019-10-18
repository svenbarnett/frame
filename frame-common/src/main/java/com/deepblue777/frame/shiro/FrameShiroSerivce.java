package com.deepblue777.frame.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.Map;

/**
 * 一句话简单描述
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/18 11:02
 * @since 1.0
 */
public interface FrameShiroSerivce {
  Map<String, String> loadFilterChainDefinitions();

  void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean);

}

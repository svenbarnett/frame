package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.mapper.FrameUserMapper;
import com.deepblue777.frame.service.FrameUserService;
import com.deepblue777.frame.shiro.FrameShiroSerivce;
import com.deepblue777.frame.utils.MD5Util;
import com.deepblue777.frame.utils.SpringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 12:41
 * @since 1.0
 */
@Service
public class FrameUserServiceImpl implements FrameUserService {

  private final static Logger LOGGER = LoggerFactory.getLogger(FrameUserServiceImpl.class);


  @Resource
  private FrameUserMapper frameUserMapper;

  @Autowired
  private FrameShiroSerivce frameShiroService;

  @Override
  public FrameUser findByLoginid(String loginid) {
    return frameUserMapper.findByLoginid(loginid);
  }

  @Override
  public BaseResponse doLogin(String loginid, String password) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(loginid, MD5Util.ecrypt(password));
    try {
      subject.login(token);
    } catch (AuthenticationException e) {
      String message;
      if (e.getMessage().length() > 13) {
        message = "用户名或密码错误";
      } else {
        message = e.getMessage();
      }
      return new BaseResponse(403, message);
    } catch (Exception e) {
      return new BaseResponse(500, "未知错误");
    }
    FrameUser user = (FrameUser) subject.getPrincipal();
    return new BaseResponse<>(user);
  }

  @Override
  public void logout() {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    ShiroFilterFactoryBean shiroFilter = (ShiroFilterFactoryBean) SpringUtil.getBean("shiroFilter");
    frameShiroService.updatePermission(shiroFilter);
  }
}


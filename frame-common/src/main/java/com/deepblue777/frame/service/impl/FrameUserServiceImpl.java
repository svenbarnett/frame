package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.dao.FrameUserDAO;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.service.FrameUserService;
import com.deepblue777.frame.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

  @Autowired
  private FrameUserDAO frameUserDAO;

  @Override
  public BaseResponse doLogin(String loginid, String password) {
    LOGGER.debug("#### 当前登录用户：{}", loginid);
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
    return new BaseResponse<>(0, "登录成功！");
  }

  @Override
  public BaseResponse logout() {
    Subject subject = SecurityUtils.getSubject();
    FrameUser user = (FrameUser) subject.getPrincipals().getPrimaryPrincipal();
    LOGGER.debug("#### 当前登出用户：{}", user.getLoginid());
    subject.logout();
    return new BaseResponse<>(0, "登出成功！");
  }

  @Override
  public List<FrameUser> findByMaps(Map map) {
    return frameUserDAO.findByMaps(map);
  }

  @Override
  public int findCountByMaps(Map map) {
    return frameUserDAO.findCountByMaps(map);
  }

  @Override
  public FrameUser findByLoginid(String loginid) {
    return frameUserDAO.findByLoginid(loginid);
  }
}


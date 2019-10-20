package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.mapper.FrameUserMapper;
import com.deepblue777.frame.service.FrameUserService;
import com.deepblue777.frame.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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

  @Resource
  private FrameUserMapper frameUserMapper;

  @Override
  public FrameUser findByLoginid(String loginid) {
    return frameUserMapper.findByLoginid(loginid);
  }

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
  public Mapper<FrameUser> getMapper() {
    return frameUserMapper;
  }

  @Override
  public List<FrameUser> findByMaps(Map map) {
    Example example = combineExampleByMap(map);
    // 分页
    Integer page = 1;
    Integer limit = 20;
    if (StringUtils.isNotBlank(map.get("page").toString())) {
      page = (Integer) map.get("page");
    }
    if (StringUtils.isNotBlank(map.get("limit").toString())) {
      limit = (Integer) map.get("limit");
    }
    RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
    List<FrameUser> users;
    users = frameUserMapper.selectByExampleAndRowBounds(example, rowBounds);
    return users;
  }

  @Override
  public int findCountByMaps(Map map) {
    Example example = combineExampleByMap(map);
    return frameUserMapper.selectCountByExample(example);
  }

  private Example combineExampleByMap(Map map) {
    Example example = new Example(FrameUser.class);
    Example.Criteria criteria = example.createCriteria();
    // 查询条件
    if (map.get("id") != null && map.get("id") != "") {
      criteria.andEqualTo("id", Integer.parseInt(map.get("id").toString()));
    }

    if (map.get("username") != null && map.get("username") != "") {
      criteria.andLike("username", "%" + map.get("username").toString() + "%");
    }

    if (map.get("email") != null && map.get("email") != "") {
      criteria.andLike("email", map.get("email").toString());
    }

    if (map.get("gender") != null && map.get("gender") != "") {
      criteria.andEqualTo("gender", Integer.parseInt(map.get("gender").toString()));
    }

    criteria.andIsNull("deleteTime");
    example.orderBy("createTime").asc();

    return example;
  }
}


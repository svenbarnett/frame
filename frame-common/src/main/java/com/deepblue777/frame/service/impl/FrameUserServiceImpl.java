package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.mapper.FrameUserMapper;
import com.deepblue777.frame.service.FrameUserService;
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

  @Resource
  private FrameUserMapper frameUserMapper;

  @Override
  public FrameUser findByLoginid(String loginid) {
    return frameUserMapper.findByLoginid(loginid);
  }
}

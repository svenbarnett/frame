package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.mapper.FrameModuleMapper;
import com.deepblue777.frame.mapper.FrameUserMapper;
import com.deepblue777.frame.service.FrameUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
  private FrameModuleMapper frameModuleMapper;

  @Resource
  private FrameUserMapper frameUserMapper;

  @Override
  public FrameUser findByLoginid(String loginid) {
     List<FrameModule> modules =  frameModuleMapper.selectAll();
    System.out.println(modules);
    return frameUserMapper.findByLoginid(loginid);
  }
}

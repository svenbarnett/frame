package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.domain.FramePermission;
import com.deepblue777.frame.mapper.FramePermissionMapper;
import com.deepblue777.frame.service.FramePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 框架权限服务实现类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/20 11:25
 * @since 1.0
 */
@Service
public class FramePermissionServiceImpl implements FramePermissionService {

  @Resource
  private FramePermissionMapper framePermissionMapper;

  @Override
  public List<FramePermission> findAllPermissions() {
    return framePermissionMapper.findAllPermissions();
  }
}

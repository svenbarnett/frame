package com.deepblue777.frame.service;

import com.deepblue777.frame.domain.FramePermission;

import java.util.List;

/**
 * 框架权限服务接口
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/20 11:23
 * @since 1.0
 */
public interface FramePermissionService {
  List<FramePermission> findAllPermissions();
}

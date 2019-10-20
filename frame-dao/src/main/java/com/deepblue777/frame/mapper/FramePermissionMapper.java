package com.deepblue777.frame.mapper;

import com.deepblue777.frame.domain.FramePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 框架权限mapper
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/20 11:16
 * @since 1.0
 */
@Mapper
public interface FramePermissionMapper {
  List<FramePermission> findAllPermissions();
}

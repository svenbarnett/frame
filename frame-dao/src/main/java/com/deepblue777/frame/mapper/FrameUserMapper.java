package com.deepblue777.frame.mapper;

import com.deepblue777.frame.domain.FrameUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户mapper
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 12:37
 * @since 1.0
 */
public interface FrameUserMapper {
  FrameUser findByLoginid(@Param("loginid") String loginid);
}

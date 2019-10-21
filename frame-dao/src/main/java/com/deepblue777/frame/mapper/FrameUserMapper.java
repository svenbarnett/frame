package com.deepblue777.frame.mapper;

import com.deepblue777.frame.domain.FrameUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 框架用户多表关联mapper
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 12:37
 * @since 1.0
 */
public interface FrameUserMapper extends Mapper<FrameUser> {
  FrameUser findByLoginid(@Param("loginid") String loginid);
}

package com.deepblue777.frame.service;

import com.deepblue777.frame.domain.FrameUser;

/**
 * 一句话简单描述
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 12:39
 * @since 1.0
 */
public interface FrameUserService {
  FrameUser findByLoginid(String loginid);
}

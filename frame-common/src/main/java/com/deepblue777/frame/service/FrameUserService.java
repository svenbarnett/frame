package com.deepblue777.frame.service;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.FrameUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 一句话简单描述
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 12:39
 * @since 1.0
 */
public interface FrameUserService {

  BaseResponse doLogin(String loginid, String password);

  BaseResponse logout();

  List<FrameUser> findByMaps(Map map);

  int findCountByMaps(Map map);

  FrameUser findByLoginid(String loginid);
}

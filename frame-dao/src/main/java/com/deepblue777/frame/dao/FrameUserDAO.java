package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.FrameUser;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 框架用户dao接口类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 9:57
 * @since 1.0
 */
public interface FrameUserDAO {

  FrameUser findByLoginid(String loginid);

  List<FrameUser> findByMaps(Map map);

  int findCountByMaps(Map map);

  FrameUser findByID(BigInteger id);

  void update(FrameUser frameUser);
}

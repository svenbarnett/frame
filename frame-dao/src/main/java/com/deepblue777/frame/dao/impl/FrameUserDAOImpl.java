package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.FrameUserDAO;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.mapper.FrameUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 框架用户dao实现类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 10:01
 * @since 1.0
 */
@Repository
public class FrameUserDAOImpl implements FrameUserDAO {

  @Resource
  private FrameUserMapper frameUserMapper;

  @Override
  public FrameUser findByLoginid(String loginid) {
    return frameUserMapper.findByLoginid(loginid);
  }

  @Override
  public List<FrameUser> findByMaps(Map map) {
    Example example = combineExampleByMap(map);
    // 分页
    Integer page = 1;
    Integer limit = 20;
    if (StringUtils.isNotBlank(map.get("page").toString())) {
      page = (Integer) map.get("page");
    }
    if (StringUtils.isNotBlank(map.get("limit").toString())) {
      limit = (Integer) map.get("limit");
    }
    RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
    List<FrameUser> users;
    users = frameUserMapper.selectByExampleAndRowBounds(example, rowBounds);
    return users;
  }

  @Override
  public int findCountByMaps(Map map) {
    Example example = combineExampleByMap(map);
    return frameUserMapper.selectCountByExample(example);
  }

  @Override
  public FrameUser findByID(BigInteger id) {
    return frameUserMapper.selectByPrimaryKey(id);
  }

  @Override
  public void update(FrameUser frameUser) {
    frameUserMapper.updateByPrimaryKeySelective(frameUser);
  }

  private Example combineExampleByMap(Map map) {
    Example example = new Example(FrameUser.class);
    Example.Criteria criteria = example.createCriteria();
    // 查询条件
    if (map.get("id") != null && map.get("id") != "") {
      criteria.andEqualTo("id", Integer.parseInt(map.get("id").toString()));
    }

    if (map.get("username") != null && map.get("username") != "") {
      criteria.andLike("username", "%" + map.get("username").toString() + "%");
    }

    if (map.get("email") != null && map.get("email") != "") {
      criteria.andLike("email", map.get("email").toString());
    }

    if (map.get("gender") != null && map.get("gender") != "") {
      criteria.andEqualTo("gender", Integer.parseInt(map.get("gender").toString()));
    }
    criteria.andIsNull("deleteTime");
    example.orderBy("createTime").asc();
    return example;
  }
}

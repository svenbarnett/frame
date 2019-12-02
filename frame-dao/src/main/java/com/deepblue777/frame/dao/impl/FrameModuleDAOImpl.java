package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.FrameModuleDAO;
import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.mapper.FrameModuleMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 框架模块dao实现
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 14:23
 * @since 1.0
 */
@Repository
public class FrameModuleDAOImpl implements FrameModuleDAO {

  @Resource
  private FrameModuleMapper frameModuleMapper;

  @Override
  public List<FrameModule> findAll() {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    return frameModuleMapper.selectByExample(example);
  }

  @Override
  public List<FrameModule> findListByPid(int pid) {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    criteria.andEqualTo("pid", pid);
    criteria.orEqualTo("id", pid);
    example.orderBy("ordernum").asc();
    example.orderBy("createTime").asc();
    return frameModuleMapper.selectByExample(example);
  }

  @Override
  public List<FrameModule> findListByPid(int pid, int page, int limit) {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    criteria.andEqualTo("pid", pid);
    criteria.orEqualTo("id", pid);
    example.orderBy("ordernum").asc();
    example.orderBy("createTime").asc();
    int start = (page - 1) * limit;
    RowBounds rowBounds = new RowBounds(start, limit);
    return frameModuleMapper.selectByExampleAndRowBounds(example, rowBounds);
  }

  @Override
  public List<FrameModule> findList(int page, int limit) {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    example.orderBy("ordernum").asc();
    example.orderBy("createTime").asc();
    int start = (page - 1) * limit;
    RowBounds rowBounds = new RowBounds(start, limit);
    return frameModuleMapper.selectByExampleAndRowBounds(example, rowBounds);
  }

  @Override
  public int findCountByPid(int pid) {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    criteria.andEqualTo("pid", pid);
    criteria.orEqualTo("id", pid);
    return frameModuleMapper.selectCountByExample(example);
  }

  @Override
  public int findCount() {
    Example example = new Example(FrameModule.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNull("deleteTime");
    return frameModuleMapper.selectCountByExample(example);
  }

  @Override
  public FrameModule findByID(int id) {
    return frameModuleMapper.selectByPrimaryKey(id);
  }

  @Override
  public int add(FrameModule frameModule) {
    return frameModuleMapper.insertSelective(frameModule);
  }

  @Override
  public void deleteById(int id, boolean softdelete) {
    if (softdelete){
      FrameModule frameModule = frameModuleMapper.selectByPrimaryKey(id);
      frameModule.setDeleteTime(new Date());
      frameModuleMapper.updateByPrimaryKeySelective(frameModule);
      return;
    }
    frameModuleMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void deleteById(int id) {
    deleteById(id,true);
  }
}

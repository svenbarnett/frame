package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.NdStudentDAO;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.mapper.NdStudentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生数据库dao实现类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 10:34
 * @since 1.0
 */
@Repository
public class NdStudentDAOImpl implements NdStudentDAO {

  @Resource
  private NdStudentMapper ndStudentMapper;

  @Override
  public List<NdStudent> findAll(Map<String, Object> map, int page, int limit) {
    Example example = new Example(NdStudent.class);
    Example.Criteria criteria = example.createCriteria();
    example.orderBy("create_time").asc();
    int start = (page - 1) * limit;
    RowBounds rowBounds = new RowBounds(start, limit);
    return ndStudentMapper.selectByExampleAndRowBounds(example, rowBounds);
  }

  @Override
  public NdStudent findByID(int id) {
    return ndStudentMapper.selectByPrimaryKey(id);
  }

  @Override
  public NdStudent findByNumber(String number) {
    Example example = new Example(NdStudent.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIsNotNull("delete_time");
    criteria.andEqualTo("number", number);
    return ndStudentMapper.selectOneByExample(example);
  }

  @Override
  public NdStudent findByIdcard(String idcard) {
    Example example = new Example(NdStudent.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("idcard", idcard);
    criteria.andIsNotNull("delete_time");
    return ndStudentMapper.selectOneByExample(example);
  }

  @Override
  public void update(NdStudent student) {
    ndStudentMapper.updateByPrimaryKey(student);
  }

  @Override
  public void delete(int id, boolean softdelte) {
    NdStudent student;
    if (softdelte) {
      student = findByID(id);
      student.setDeleteTime(new Date());
      ndStudentMapper.updateByPrimaryKey(student);
    } else {
      ndStudentMapper.deleteByPrimaryKey(id);
    }
  }

  @Override
  public void delete(int id) {
    this.delete(id, true);
  }
}

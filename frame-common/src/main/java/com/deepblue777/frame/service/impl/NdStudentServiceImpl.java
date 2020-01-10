package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.dao.NdStudentDAO;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.service.NdStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学生service 实现类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 13:20
 * @since 1.0
 */
@Service
public class NdStudentServiceImpl implements NdStudentService {

  @Autowired
  private NdStudentDAO ndStudentDAO;

  @Override
  public List<NdStudent> getTables(Map<String, Object> map, int page, int limit) {
    List<NdStudent> students = ndStudentDAO.findAll(map, page, limit);
    return students;
  }

  @Override
  public int getTablesCount(Map<String, Object> map) {
    return ndStudentDAO.findAllCount(map);
  }

  @Override
  public NdStudent getStudentByID(String id) {
    return ndStudentDAO.findByID(id);
  }

  @Override
  public NdStudent getStudentByNumber(String number) {
    return ndStudentDAO.findByNumber(number);
  }

  @Override
  public void add(NdStudent ndStudent) {
    ndStudentDAO.add(ndStudent);
  }

  @Override
  public void update(NdStudent ndStudent) {
    ndStudentDAO.update(ndStudent);
  }

  @Override
  public void delete(String id) {
    ndStudentDAO.delete(id,false);
  }

  @Override
  public List<NdStudent> findAll() {
    return ndStudentDAO.findAll();
  }
}

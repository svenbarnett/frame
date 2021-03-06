package com.deepblue777.frame.service;

import com.deepblue777.frame.domain.NdStudent;

import java.util.List;
import java.util.Map;

/**
 * 学生管理service接口
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 13:18
 * @since 1.0
 */
public interface NdStudentService {

  List<NdStudent> getTables(Map<String, Object> map, int page, int limit);

  int getTablesCount(Map<String, Object> map);

  NdStudent getStudentByID(String id);

  NdStudent getStudentByNumber(String number);

  NdStudent getStudentByIdNumber(String idnumber);

  void add(NdStudent ndStudent);

  void update(NdStudent ndStudent);

  void delete(String id);

  List<NdStudent> findAll();
}

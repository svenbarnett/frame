package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.NdStudent;

import java.util.List;
import java.util.Map;

/**
 * 学生数据库dao接口
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 10:29
 * @since 1.0
 */
public interface NdStudentDAO {
  /**
   * 分页、条件查询所有
   *
   * @param map   条件
   * @param page  页码
   * @param limit 每页数据
   * @return 按照时间顺序列表
   */
  List<NdStudent> findAll(Map<String, Object> map, int page, int limit);

  /**
   * 根据id查询学生
   *
   * @param id 学生主键
   * @return 学生信息
   */
  NdStudent findByID(int id);

  /**
   * 根据学生学号查询
   *
   * @param number 学号
   * @return 学生信息
   */
  NdStudent findByNumber(String number);

  /**
   * 根据学生身份证查询
   *
   * @param idcard 身份证
   * @return 学生信息
   */
  NdStudent findByIdcard(String idcard);

  /**
   * 更新学生信息
   *
   * @param student 学生实体
   */
  void update(NdStudent student);

  /**
   * 根据标识进行软 硬删除
   *
   * @param id        学生主键
   * @param softdelte 是否软删除 true软删除
   */
  void delete(int id, boolean softdelte);

  /**
   * 根据id主键软删除
   *
   * @param id 学生主键
   */
  void delete(int id);


}

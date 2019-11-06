package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.NdExamCourse;
import com.deepblue777.frame.domain.NdExamInfo;

import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 17:39]
 */
public interface NdExamCourseDAO {
    /**
     * 查询列表
     *
     * @param map   条件
     * @param page  分页
     * @param limit 每页数据
     * @return 成绩列表
     */
    List<NdExamCourse> findAll(Map<String, Object> map, int page, int limit);

    /**
     * 查询总数
     *
     * @param map 条件
     * @return 总数
     */
    int findAllCount(Map<String, Object> map);

    void add(NdExamCourse course);

}

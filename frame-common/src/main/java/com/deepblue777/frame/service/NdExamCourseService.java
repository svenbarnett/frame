package com.deepblue777.frame.service;

import com.deepblue777.frame.domain.NdExamCourse;

import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-06 21:43]
 */
public interface NdExamCourseService {

    List<NdExamCourse> getTables(Map<String,Object> map, int page, int limit);

    int getTablesCount(Map<String,Object> map);

    void add(NdExamCourse course);

    void delete(int id);

    NdExamCourse findById(int id);

    void update(NdExamCourse course);
}

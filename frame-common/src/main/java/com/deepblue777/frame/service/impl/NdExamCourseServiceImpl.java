package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.dao.NdExamCourseDAO;
import com.deepblue777.frame.dao.impl.NdExamCourseDAOImpl;
import com.deepblue777.frame.domain.NdExamCourse;
import com.deepblue777.frame.service.NdExamCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-06 21:44]
 */
@Service
public class NdExamCourseServiceImpl implements NdExamCourseService {

    @Autowired
    private NdExamCourseDAO ndExamCourseDAO;

    @Override
    public List<NdExamCourse> getTables(Map<String, Object> map, int page, int limit) {
        return ndExamCourseDAO.findAll(map, page, limit);
    }

    @Override
    public int getTablesCount(Map<String, Object> map) {
        return ndExamCourseDAO.findAllCount(map);
    }

    @Override
    public void add(NdExamCourse course) {
        ndExamCourseDAO.add(course);
    }

    @Override
    public void delete(String id) {
        ndExamCourseDAO.delete(id);
    }

    @Override
    public NdExamCourse findById(String id) {
        return ndExamCourseDAO.findById(id);
    }

    @Override
    public void update(NdExamCourse course) {
        course.setUpdateTime(new Date());
        ndExamCourseDAO.update(course);
    }
}

package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.BaseDAO;
import com.deepblue777.frame.dao.NdExamCourseDAO;
import com.deepblue777.frame.domain.NdExamCourse;
import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.mapper.NdExamCourseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-06 21:36]
 */
@Repository
public class NdExamCourseDAOImpl extends BaseDAO implements NdExamCourseDAO {


    @Resource
    private NdExamCourseMapper ndExamCourseMapper;

    @Override
    public List<NdExamCourse> findAll(Map<String, Object> map, int page, int limit) {
        Example example = this.combineExample(map, NdExamCourse.class);
        int start = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(start, limit);
        return ndExamCourseMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public int findAllCount(Map<String, Object> map) {
        Example example = this.combineExample(map, NdExamCourse.class);
        return ndExamCourseMapper.selectCountByExample(example);
    }

    @Override
    public void add(NdExamCourse course) {
        ndExamCourseMapper.insert(course);
    }

    @Override
    public void delete(int id, boolean softdelete) {
        if (softdelete) {
            NdExamCourse course = findById(id);
            course.setDeleteTime(new Date());
            ndExamCourseMapper.updateByPrimaryKey(course);
        } else {
            ndExamCourseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void delete(int id) {
        this.delete(id,true);
    }

    @Override
    public NdExamCourse findById(int id) {
        return ndExamCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(NdExamCourse course) {
        ndExamCourseMapper.updateByPrimaryKeySelective(course);
    }
}

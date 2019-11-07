package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.dao.NdExamInfoDAO;
import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.exception.BusinessException;
import com.deepblue777.frame.service.NdExamInfoService;
import com.deepblue777.frame.service.NdStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 14:21]
 */

@Service
public class NdExamInfoServiceImpl implements NdExamInfoService {
    @Autowired
    private NdExamInfoDAO ndExamInfoDAO;

    @Autowired
    private NdStudentService studentService;

    @Override
    public List<NdExamInfo> getTables(Map<String, Object> map, int page, int limit) {
        return ndExamInfoDAO.findAll(map, page, limit);
    }

    @Override
    public int getTablesCount(Map<String, Object> map) {
        return ndExamInfoDAO.findAllCount(map);
    }

    @Override
    public List<Map<String, String>> getSutdentsCode() {
        List<Map<String,String>> list = new ArrayList<>();
        List<NdStudent> students = studentService.findAll();
        for (int i = 0; i < students.size(); i++) {
            Map<String,String> map = new HashMap<>(2);
            NdStudent student = students.get(i);
            map.put("studentName",student.getName());
            map.put("studentNumber",student.getNumber());
            list.add(map);
        }
        return list;
    }

    @Override
    public void add(NdExamInfo ndExamInfo) {
        ndExamInfoDAO.add(ndExamInfo);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void delete(int id) {
        ndExamInfoDAO.deleteById(id);
        // TODO 需要删除对应的成绩的课程成绩分数
    }

    @Override
    public NdExamInfo findById(int infoId) {
        return ndExamInfoDAO.findById(infoId);
    }

    @Override
    public void update(NdExamInfo info) {
        info.setUpdateTime(new Date());
        ndExamInfoDAO.update(info);
    }
}

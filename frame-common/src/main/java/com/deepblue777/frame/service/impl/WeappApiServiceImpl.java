package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.service.NdExamInfoService;
import com.deepblue777.frame.service.NdStudentService;
import com.deepblue777.frame.service.WeappApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * api 小程序实现类
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-16 15:15]
 */
@Service
public class WeappApiServiceImpl implements WeappApiService {


    @Autowired
    private NdStudentService ndStudentService;

    @Autowired
    private NdExamInfoService ndExamInfoService;

    @Override
    public Map<String, Object> getExaminfoByNumber(String number,String year,Integer term) {
        Map<String,Object> resMap = new HashMap<>(10);
        NdStudent student = ndStudentService.getStudentByNumber(number);
        resMap.put("name",student.getName());
        resMap.put("number",student.getNumber());
        resMap.put("major",student.getMajor());
        resMap.put("classname",student.getClassname());
        NdExamInfo info = ndExamInfoService.findByStudentNumber(number,year,term);
        if (info != null){
            resMap.put("year",info.getYear());
            resMap.put("term",info.getTerm());
            resMap.put("gpa",info.getGpa());
            resMap.put("rank",info.getRank());
            resMap.put("courses",info.getCourses());
        }
        return resMap;
    }

    @Override
    public boolean checkStuValid(String name, String number, String idnumber) {
        NdStudent student = ndStudentService.getStudentByNumber(number);
        if (student == null || !name.equals(student.getName()) || !idnumber.equals(student.getIdcard())){
            return false;
        }
        return true;
    }
}

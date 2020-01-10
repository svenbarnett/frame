package com.deepblue777.frame.service;

import com.deepblue777.frame.domain.NdExamInfo;

import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 14:20]
 */

public interface NdExamInfoService {

    List<NdExamInfo> getTables(Map<String, Object> map, int page, int limit);

    int getTablesCount(Map<String, Object> map);

    List<Map<String,String>> getSutdentsCode();

    void add(NdExamInfo ndExamInfo);

    void delete(String id);

    NdExamInfo findById(String infoId);

    NdExamInfo findByStudentNumber(String studentNumber,String year,Integer term);

    void update(NdExamInfo info);
}

package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.mapper.NdExamInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 成绩表dao
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-10-30 20:53]
 */
public interface NdExamInfoDAO{


    void add(NdExamInfo ndExamInfo);

    /**
     * 查询列表
     *
     * @param map   条件
     * @param page  分页
     * @param limit 每页数据
     * @return 成绩列表
     */
    List<NdExamInfo> findAll(Map<String, Object> map, int page, int limit);

    /**
     * 查询总数
     *
     * @param map 条件
     * @return 总数
     */
    int findAllCount(Map<String, Object> map);

    /**
     * 根据id查询
     *
     * @param id 成绩主键
     * @return
     */
    NdExamInfo findById(int id);

    /**
     * 根据examle的条件查询
     *
     * @param map 条件
     * @return
     */
    NdExamInfoMapper findByMap(Map<String, Object> map);

    void update(NdExamInfo ndExamInfo);

    void deleteById(int id);

    void deleteById(int id, boolean softdelete);
}

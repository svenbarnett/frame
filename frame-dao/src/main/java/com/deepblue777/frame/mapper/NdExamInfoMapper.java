package com.deepblue777.frame.mapper;

import com.deepblue777.frame.domain.NdExamInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 成绩表集成多表查询
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-10-30 20:51]
 */

public interface NdExamInfoMapper extends Mapper<NdExamInfo> {

    /**
     * 根据学号获取相关课程信息
     * @param studentNumber 学号
     * @return
     */
    NdExamInfo findByStudentNumber(@Param("studentNumber") String studentNumber,
                                   @Param("year") String year,
                                   @Param("term") Integer term);

}

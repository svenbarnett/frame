package com.deepblue777.frame.domain;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 科目表
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 10:15
 * @since 1.0
 */
@Data
@Table(name = "nd_exam_course")
public class NdExamCourse {

    @Id
    private String id;
    private String examInfoId;
    private String name;
    private Float weight;
    private String score;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;

}

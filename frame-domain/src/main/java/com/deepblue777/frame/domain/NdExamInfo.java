package com.deepblue777.frame.domain;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "nd_exam_info")
public class NdExamInfo {

    @Id
    private String id;
    private String year;
    private Integer term;
    private String studentName;
    private String studentNumber;
    private Float allscore;
    private Float getscore;
    private Float gpa;
    private Integer rank;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Set<NdExamCourse> courses = new HashSet<>();

}

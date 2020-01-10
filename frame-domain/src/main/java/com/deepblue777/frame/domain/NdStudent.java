package com.deepblue777.frame.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 学生实体类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 10:15
 * @since 1.0
 */
@Data
@Table(name = "nd_student")
public class NdStudent {
  @Id
  private String id;
  private String name;
  private String year;
  private String major;
  private String classname;
  private String number;
  private String idcard;
  private Integer gender;
  private String email;
  private Integer status;
  private Date createTime;
  private Date updateTime;
  private Date deleteTime;
}

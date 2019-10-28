package com.deepblue777.frame.domain;

import lombok.Data;

import java.util.Date;

/**
 * 学生实体类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 10:15
 * @since 1.0
 */
@Data
public class NdStudent {
  private Integer id;
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

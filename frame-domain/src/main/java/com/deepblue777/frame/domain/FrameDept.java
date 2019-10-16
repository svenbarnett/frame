package com.deepblue777.frame.domain;

import lombok.Data;

import java.util.Date;

/**
 * 框架部门表
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/16 14:33
 * @since 1.0
 */
@Data
public class FrameDept {

  private Integer id;

  private Integer pid;

  private String name;

  private Integer status;

  private Integer ordernum;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;

}

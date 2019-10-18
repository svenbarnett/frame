package com.deepblue777.frame.domain;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 框架用户表
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 11:11
 * @since 1.0
 */
@Data
public class FrameUser {

  private Integer id;

  private String loginid;

  private String username;

  private String password;

  private String contacts;

  private String mobile;

  private Integer gender;

  private String email;

  private Integer status;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;

  private Set<FrameRole> roles = new HashSet<>();

  private String updater;
}

package com.deepblue777.frame.domain;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 框架角色表
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 11:12
 * @since 1.0
 */
@Data
public class FrameRole {
  private Integer rid;

  private String roleName;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;

  private Set<FramePermission> permissions = new HashSet<>();

  private Set<FrameUser> users = new HashSet<>();

}

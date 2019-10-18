package com.deepblue777.frame.domain;

import lombok.Data;

import java.util.Date;

/**
 * 框架权限
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 11:12
 * @since 1.0
 */
@Data
public class FramePermission {
  private Integer id;

  private String permissionName;

  private String uri;

  private Integer pid;

  private String type;

  private String creator;

  private String updater;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;
}

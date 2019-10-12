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
  private Integer pid;

  private String permissionName;

  private String url;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;
}

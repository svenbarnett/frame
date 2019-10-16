package com.deepblue777.frame.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 框架菜单表
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/16 14:35
 * @since 1.0
 */
@Data
@Table(name = "frame_module")
public class FrameModule {

  // @Id表示该字段对应数据库表的主键id
  // @GeneratedValue中strategy表示使用数据库自带的主键生成策略.
  // @GeneratedValue中generator配置为"JDBC",在数据插入完毕之后,会自动将主键id填充到实体类中.类似普通mapper.xml中配置的selectKey标签
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
  private Integer id;

  private Integer pid;

  private String name;

  private String icon;

  private String url;

  private String percode;

  private Integer ordernum;

  private Integer status;

  private Date createTime;

  private Date updateTime;

  private Date deleteTime;
}

package com.deepblue777.frame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * dtree的视图模型
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 14:05
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class TreeListVO {

  private int id;
  private String title;
  private String checkArr;
  private int parentId;
  private String iconClass;
}

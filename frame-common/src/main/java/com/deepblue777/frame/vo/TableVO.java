package com.deepblue777.frame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据表格视图对象
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/20 17:22
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class TableVO<T> {

  private int code;
  private String msg;
  private int count;
  private T data;

  public TableVO(int count, T data) {
    this.code = 0;
    this.msg = "操作成功！";
    this.count = count;
    this.data = data;
  }


}

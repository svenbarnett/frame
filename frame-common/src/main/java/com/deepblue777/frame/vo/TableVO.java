package com.deepblue777.frame.vo;

/**
 * 数据表格视图对象
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/20 17:22
 * @since 1.0
 */
public class TableVO<T> {

  private int code;
  private String msg;
  private int count;
  private T data;

  public TableVO(int code, String msg, int count, T data) {
    this.code = code;
    this.msg = msg;
    this.count = count;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getcount() {
    return count;
  }

  public void setcount(int count) {
    this.count = count;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}

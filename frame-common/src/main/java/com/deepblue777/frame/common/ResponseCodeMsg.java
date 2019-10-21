package com.deepblue777.frame.common;

/**
 * 请求响应码和消息
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 13:25
 * @since 1.0
 */
public enum ResponseCodeMsg {
  NO_LOGIN(207, "未登录，请登录！"),
  NO_PERMISSION(403, "您没有改操作的权限！");

  private int code;

  private String msg;

  ResponseCodeMsg() {
  }

  ResponseCodeMsg(int code, String msg) {
    this.code = code;
    this.msg = msg;
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
}

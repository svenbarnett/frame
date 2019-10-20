package com.deepblue777.frame.common;

public class BaseResponse<T> {
  private int code = 1;
  private String msg = "SUCCESS";
  protected T data;

  public BaseResponse() {
  }

  public BaseResponse(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public BaseResponse(int code, String msg, T data) {
    this(code, msg);
    this.data = data;
  }

  public BaseResponse(T data) {
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getmsg() {
    return msg;
  }

  public void setmsg(String msg) {
    this.msg = msg;
  }

  public T getdata() {
    return data;
  }

  public void setdata(T data) {
    this.data = data;
  }
}

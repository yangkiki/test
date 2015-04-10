package com.moxian.ng.model;

import java.io.Serializable;

/**
 * Created by gao on 15-4-10.
 */
public class SingleResponse<T> implements Serializable {

  private boolean result;

  private String code;

  private String msg;

  private long date;

  private T data;

  public SingleResponse(boolean result, String msg, long date) {
    this.result = result;
    this.msg = msg;
    this.date = date;
  }


  public static SingleResponse successRsp() {
    SingleResponse response = new SingleResponse(true, "success", System.currentTimeMillis() / 1000);
    return response;
  }

  public static SingleResponse failRsp() {
    SingleResponse response = new SingleResponse(false, "failed", System.currentTimeMillis() / 1000);
    return response;
  }


  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "SingleResponse{" +
           "result=" + result +
           ", code='" + code + '\'' +
           ", msg='" + msg + '\'' +
           ", date=" + date +
           ", data=" + data +
           '}';
  }
}

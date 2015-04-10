package com.moxian.ng.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gao on 15-4-10.
 */
public class ListResponse<T> implements Serializable {

  private boolean result;

  private String code;

  private String msg;

  private long date;

  private List<T> data;

  private long totalCount;

  public ListResponse(boolean result, String msg, long date) {
    this.result = result;
    this.msg = msg;
    this.date = date;
  }
  public ListResponse(boolean result, String msg, long date,String code) {
    this.result = result;
    this.msg = msg;
    this.date = date;
    this.code=code;
  }


  public static ListResponse successRsp() {
    ListResponse response = new ListResponse(true, "success", System.currentTimeMillis() / 1000,ErrorCode.SUCCESS);
    return response;
  }

  public static ListResponse failRsp() {
    ListResponse response = new ListResponse(false, "failed", System.currentTimeMillis() / 1000);
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

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }

  @Override
  public String toString() {
    return "ListResponse{" +
           "result=" + result +
           ", code='" + code + '\'' +
           ", msg='" + msg + '\'' +
           ", date=" + date +
           ", data=" + data +
           ", totalCount=" + totalCount +
           '}';
  }
}

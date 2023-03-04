package com.yan.demo.pojo;



import java.io.Serializable;

/**
 * @author 24129
 */

public class User implements Serializable {

  private Long userId=-1L;

  private String userName="";

  private String ipAddress="";

  private String other="";

  private String otherOne="";

  private String address="";
  private String clientType="";
  private String count="";

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }


  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }


  public String getOtherOne() {
    return otherOne;
  }

  public void setOtherOne(String otherOne) {
    this.otherOne = otherOne;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getClientType() {
    return clientType;
  }

  public void setClientType(String clientType) {
    this.clientType = clientType;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }
}

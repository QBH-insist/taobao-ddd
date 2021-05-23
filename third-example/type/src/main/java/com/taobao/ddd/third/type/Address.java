package com.taobao.ddd.third.type;

import java.io.Serializable;

public class Address implements Serializable {
  private String country;
  private String province;
  private String city;
  private String detail;

  public Address(){}

  public Address(String country, String province, String city, String detail) {
    this.country = country;
    this.province = province;
    this.city = city;
    this.detail = detail;
  }

  public String serialize() {
    return country + " " + province + " " + city + " " + detail;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return "Address{" +
        "country='" + country + '\'' +
        ", province='" + province + '\'' +
        ", city='" + city + '\'' +
        ", detail='" + detail + '\'' +
        '}';
  }

}

package com.taobao.ddd.third.application.dto;

import com.taobao.ddd.third.type.OrderId;

public class OrderDTO {
  private OrderId orderId;
  private String itemTitle;
  private String detailAddress;

  public OrderId getOrderId() {
    return orderId;
  }

  public void setOrderId(OrderId orderId) {
    this.orderId = orderId;
  }

  public String getItemTitle() {
    return itemTitle;
  }

  public void setItemTitle(String itemTitle) {
    this.itemTitle = itemTitle;
  }

  public String getDetailAddress() {
    return detailAddress;
  }

  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
}

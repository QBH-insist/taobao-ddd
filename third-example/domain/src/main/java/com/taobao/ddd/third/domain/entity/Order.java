package com.taobao.ddd.third.domain.entity;

import com.taobao.ddd.third.type.Address;
import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.type.mark.Aggregate;

import java.util.List;

public class Order implements Aggregate<OrderId> {
  private OrderId id;
  private String title;
  private Address address;
  private List<LineItem> lineItems;

  @Override
  public OrderId getId() {
    return id;
  }

  public void setId(OrderId id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setLineItems(List<LineItem> lineItems) {
    this.lineItems = lineItems;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }


  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", address=" + address +
        ", lineItems=" + lineItems +
        '}';
  }

}

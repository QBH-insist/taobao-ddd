package com.taobao.ddd.second.domain.entity;

import com.taobao.ddd.second.type.OrderId;
import com.taobao.ddd.second.type.mark.Entity;

public class LineItem implements Entity<OrderId> {
  private OrderId id;

  @Override
  public OrderId getId() {
    return id;
  }

  public void setId(OrderId id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "LineItem{" +
        "id=" + id +
        '}';
  }

}

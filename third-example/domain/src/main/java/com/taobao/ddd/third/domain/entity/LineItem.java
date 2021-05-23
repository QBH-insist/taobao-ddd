package com.taobao.ddd.third.domain.entity;

import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.type.mark.Entity;

import java.math.BigDecimal;

public class LineItem implements Entity<OrderId> {
  private OrderId id;
  private BigDecimal money;

  public LineItem() {}
  ;

  public LineItem(OrderId orderId, BigDecimal money) {
    this.id = orderId;
    this.money = money;
  }

  @Override
  public OrderId getId() {
    return id;
  }

  public void setId(OrderId id) {
    this.id = id;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  @Override
  public String toString() {
    return "LineItem{" + "id=" + id + ", money=" + money + '}';
  }
}

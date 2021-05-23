package com.taobao.ddd.third.type;

import com.taobao.ddd.third.type.mark.Identifier;

public class OrderId implements Identifier<Long> {
  public Long id;

  public OrderId() {}

  public OrderId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Long getValue() {
    return id;
  }

  @Override
  public String toString() {
    return "OrderId{" +
        "id=" + id +
        '}';
  }

}

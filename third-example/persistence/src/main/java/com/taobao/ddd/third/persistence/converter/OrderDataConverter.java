package com.taobao.ddd.third.persistence.converter;

import com.taobao.ddd.third.domain.entity.Order;
import com.taobao.ddd.third.persistence.OrderDO;

public class OrderDataConverter {
  public static final OrderDataConverter INSTANCE = new OrderDataConverter();

  public Order fromData(OrderDO orderDO) {
    return null;
  }

  public OrderDO toData(Order aggregate) {
    return null;
  }

}

package com.taobao.ddd.second.persistence.converter;

import com.taobao.ddd.second.domain.entity.Order;
import com.taobao.ddd.second.persistence.OrderDO;

public class OrderDataConverter {
  public static final OrderDataConverter INSTANCE = new OrderDataConverter();

  public Order fromData(OrderDO orderDO) {
    return null;
  }

  public OrderDO toData(Order aggregate) {
    return null;
  }

}

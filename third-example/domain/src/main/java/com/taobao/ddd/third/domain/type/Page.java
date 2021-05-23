package com.taobao.ddd.third.domain.type;

import com.taobao.ddd.third.domain.entity.Order;

import java.util.List;

public class Page<T> {

  public static Page<Order> with(List<Order> result, OrderQuery query, long count) {
    return null;
  }

}

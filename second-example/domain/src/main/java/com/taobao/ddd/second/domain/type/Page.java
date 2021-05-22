package com.taobao.ddd.second.domain.type;

import com.taobao.ddd.second.domain.entity.Order;

import java.util.List;

public class Page<T> {

  public static Page<Order> with(List<Order> result, OrderQuery query, long count) {
    return null;
  }

}

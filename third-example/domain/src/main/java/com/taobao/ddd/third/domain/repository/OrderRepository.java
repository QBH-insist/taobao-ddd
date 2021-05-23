package com.taobao.ddd.third.domain.repository;

import com.taobao.ddd.third.domain.entity.Order;
import com.taobao.ddd.third.domain.type.OrderQuery;
import com.taobao.ddd.third.domain.type.Page;
import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.type.StoreId;

public interface OrderRepository extends Repository<Order, OrderId> {
  // 自定义Count接口，在这里OrderQuery是一个自定义的DTO
  Long count(OrderQuery query);

  // 自定义分页查询接口
  Page<Order> query(OrderQuery query);

  // 自定义有多个条件的查询接口
  Order findInStore(OrderId id, StoreId storeId);
}

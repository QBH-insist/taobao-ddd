package com.taobao.ddd.third.persistence.dao;

import com.taobao.ddd.third.domain.type.OrderQuery;
import com.taobao.ddd.third.persistence.OrderDO;

import java.util.List;

public interface OrderDAO {

  OrderDO findById(long longValue);

  int delete(OrderDO orderDO);

  int update(OrderDO orderDO);

  int insert(OrderDO orderDO);

  List<OrderDO> queryPaged(OrderQuery query);

  long count(OrderQuery query);

  OrderDO findInStore(Long value, Long value1);

}

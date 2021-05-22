package com.taobao.ddd.second.persistence.dao;

import com.taobao.ddd.second.domain.type.OrderQuery;
import com.taobao.ddd.second.persistence.OrderDO;

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

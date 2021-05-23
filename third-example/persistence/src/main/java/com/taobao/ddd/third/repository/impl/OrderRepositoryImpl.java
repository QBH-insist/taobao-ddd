package com.taobao.ddd.third.repository.impl;

import com.taobao.ddd.third.domain.entity.Order;
import com.taobao.ddd.third.domain.repository.OrderRepository;
import com.taobao.ddd.third.domain.entity.LineItem;
import com.taobao.ddd.third.domain.type.OrderQuery;
import com.taobao.ddd.third.domain.type.Page;
import com.taobao.ddd.third.persistence.LineItemDO;
import com.taobao.ddd.third.persistence.OrderDO;
import com.taobao.ddd.third.persistence.converter.LineItemDataConverter;
import com.taobao.ddd.third.persistence.converter.OrderDataConverter;
import com.taobao.ddd.third.persistence.dao.LineItemDAO;
import com.taobao.ddd.third.persistence.dao.OrderDAO;
import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.type.StoreId;

import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
  private final OrderDAO orderDAO; // 具体的DAO接口
  private final OrderDataConverter converter; // 转化器
  private final LineItemDAO lineItemDAO;
  private final LineItemDataConverter lineItemConverter;

  public OrderRepositoryImpl(OrderDAO orderDAO, LineItemDAO lineItemDAO) {
    this.orderDAO = orderDAO;
    this.lineItemDAO = lineItemDAO;
    this.converter = OrderDataConverter.INSTANCE;
    this.lineItemConverter = LineItemDataConverter.INSTANCE;
  }

  @Override
  public Order find(OrderId orderId) {
    OrderDO orderDO = orderDAO.findById(orderId.getValue());
    return converter.fromData(orderDO);
  }

  @Override
  public void remove(Order aggregate) {
    OrderDO orderDO = converter.toData(aggregate);
    orderDAO.delete(orderDO);
  }

  @Override
  public void save(Order aggregate) {
    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      // 每次都将Order和所有LineItem全量更新
      OrderDO orderDO = converter.toData(aggregate);
      orderDAO.update(orderDO);
      for (LineItem lineItem : aggregate.getLineItems()) {
        save(lineItem);
      }
    } else {
      // insert
      OrderDO orderDO = converter.toData(aggregate);
      orderDAO.insert(orderDO);
      aggregate.setId(converter.fromData(orderDO).getId());
    }
  }

  private void save(LineItem lineItem) {
    LineItemDO lineItemDO = lineItemConverter.toData(lineItem);
    if (lineItem.getId() != null && lineItem.getId().getValue() > 0) {
      lineItemDAO.update(lineItemDO);
    } else {
      lineItemDAO.insert(lineItemDO);
      lineItem.setId(lineItemConverter.fromData(lineItemDO).getId());
    }
  }

  @Override
  public Page<Order> query(OrderQuery query) {
    List<OrderDO> orderDOS = orderDAO.queryPaged(query);
    long count = orderDAO.count(query);
    List<Order> result = orderDOS.stream().map(converter::fromData).collect(Collectors.toList());
    return Page.with(result, query, count);
  }

  @Override
  public Order findInStore(OrderId id, StoreId storeId) {
    OrderDO orderDO = orderDAO.findInStore(id.getValue(), storeId.getValue());
    return converter.fromData(orderDO);
  }

  @Override
  public Long count(OrderQuery query) {
    return orderDAO.count(query);
  }

  @Override
  public void attach(Order aggregate) {}

  @Override
  public void detach(Order aggregate) {}
}

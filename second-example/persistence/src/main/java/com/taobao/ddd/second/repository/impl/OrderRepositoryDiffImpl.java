package com.taobao.ddd.second.repository.impl;

import com.taobao.ddd.second.domain.entity.LineItem;
import com.taobao.ddd.second.domain.entity.Order;
import com.taobao.ddd.second.domain.repository.OrderRepository;
import com.taobao.ddd.second.domain.type.OrderQuery;
import com.taobao.ddd.second.domain.type.Page;
import com.taobao.ddd.second.persistence.LineItemDO;
import com.taobao.ddd.second.persistence.OrderDO;
import com.taobao.ddd.second.persistence.converter.LineItemDataConverter;
import com.taobao.ddd.second.persistence.converter.OrderDataConverter;
import com.taobao.ddd.second.persistence.dao.LineItemDAO;
import com.taobao.ddd.second.persistence.dao.OrderDAO;
import com.taobao.ddd.second.repository.diff.Diff;
import com.taobao.ddd.second.repository.diff.EntityDiff;
import com.taobao.ddd.second.repository.diff.ListDiff;
import com.taobao.ddd.second.type.OrderId;
import com.taobao.ddd.second.type.StoreId;

import java.util.List;
import java.util.stream.Collectors;

/** 支持跟踪的 OrderRepository 实现类 */
public class OrderRepositoryDiffImpl extends DbRepositorySupport<Order, OrderId>
    implements OrderRepository {

  private final OrderDAO orderDAO; // 具体的DAO接口
  private final OrderDataConverter converter; // 转化器
  private final LineItemDAO lineItemDAO;
  private final LineItemDataConverter lineItemConverter;

  protected OrderRepositoryDiffImpl(OrderDAO orderDAO, LineItemDAO lineItemDAO) {
    super(Order.class);
    this.orderDAO = orderDAO;
    this.lineItemDAO = lineItemDAO;
    this.converter = OrderDataConverter.INSTANCE;
    this.lineItemConverter = LineItemDataConverter.INSTANCE;
  }

  @Override
  protected void onInsert(Order aggregate) {
    OrderDO orderDO = converter.toData(aggregate);
    orderDAO.insert(orderDO);
    aggregate.setId(converter.fromData(orderDO).getId());
  }

  @Override
  protected Order onSelect(OrderId orderId) {
    OrderDO orderDO = orderDAO.findById(orderId.getValue());
    return converter.fromData(orderDO);
  }

  @Override
  protected void onUpdate(Order aggregate, EntityDiff diff) {
    if (diff.isSelfModified()) {
      OrderDO orderDO = converter.toData(aggregate);
      orderDAO.update(orderDO);
    }

    Diff lineItemDiffs = diff.getDiff("lineItems");
    if(lineItemDiffs instanceof ListDiff) {
      ListDiff diffList = (ListDiff) lineItemDiffs;
      for (Diff itemDiff : diffList) {
        // LineItem line = (LineItem) itemDiff.getOldValue();
        // LineItemDO lineDO = lineItemConverter.toData(line);
        // lineItemDAO.delete(lineDO);
      }
    }

  }

  @Override
  protected void onDelete(Order aggregate) {
    OrderDO orderDO = converter.toData(aggregate);
    orderDAO.delete(orderDO);
  }

  @Override
  public Long count(OrderQuery query) {
    return orderDAO.count(query);
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
}

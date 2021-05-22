package com.taobao.ddd.second.repository.impl;

import com.sun.istack.internal.NotNull;
import com.taobao.ddd.second.domain.repository.Repository;
import com.taobao.ddd.second.repository.diff.AggregateManager;
import com.taobao.ddd.second.repository.diff.EntityDiff;
import com.taobao.ddd.second.type.mark.Aggregate;
import com.taobao.ddd.second.type.mark.Identifier;

/** 这个类是一个通用的支撑类，为了减少开发者的重复劳动。在用的时候需要继承这个类, 属于基础设施层（模板方法） */
public abstract class DbRepositorySupport<T extends Aggregate<ID>, ID extends Identifier>
    implements Repository<T, ID> {
  private final Class<T> targetClass;

  // 让 AggregateManager 去维护 Snapshot
  private AggregateManager<T, ID> aggregateManager;

  protected DbRepositorySupport(Class<T> targetClass) {
    this.targetClass = targetClass;
    //todo 采用工厂模式创建
    // this.aggregateManager = AggregateManager.newInstance(targetClass);
  }

  /** Attach的操作就是让Aggregate可以被追踪 */
  @Override
  public void attach(@NotNull T aggregate) {
    this.aggregateManager.attach(aggregate);
  }

  /** Detach的操作就是让Aggregate停止追踪 */
  @Override
  public void detach(@NotNull T aggregate) {
    this.aggregateManager.detach(aggregate);
  }

  @Override
  public T find(@NotNull ID id) {
    T aggregate = this.onSelect(id);
    if (aggregate != null) {
      // 这里的就是让查询出来的对象能够被追踪。
      // 如果自己实现了一个定制查询接口，要记得单独调用attach。
      this.attach(aggregate);
    }
    return aggregate;
  }

  @Override
  public void remove(@NotNull T aggregate) {
    this.onDelete(aggregate);
    // 删除停止追踪
    this.detach(aggregate);
  }

  @Override
  public void save(@NotNull T aggregate) {
    // 如果没有 ID，直接插入
    if (aggregate.getId() == null) {
      this.onInsert(aggregate);
      this.attach(aggregate);
      return;
    }

    // 做 Diff
    EntityDiff diff = null;
    try {
      diff = aggregateManager.detectChanges(aggregate);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    if (diff.isEmpty()) {
      return;
    }

    // 调用 UPDATE
    this.onUpdate(aggregate, diff);

    // 最终将 DB 带来的变化更新回 AggregateManager
    aggregateManager.merge(aggregate);
  }

  /** 这几个方法是继承的子类应该去实现的 */
  protected abstract void onInsert(T aggregate);

  protected abstract T onSelect(ID id);

  protected abstract void onUpdate(T aggregate, EntityDiff diff);

  protected abstract void onDelete(T aggregate);

  public Class<T> getTargetClass() {
    return targetClass;
  }

  public AggregateManager<T, ID> getAggregateManager() {
    return aggregateManager;
  }

  public void setAggregateManager(AggregateManager<T, ID> aggregateManager) {
    this.aggregateManager = aggregateManager;
  }
}

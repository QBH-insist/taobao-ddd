package com.taobao.ddd.third.repository.diff;

public interface AggregateManager<T, ID> {


  void attach(T aggregate);

  void attach(T aggregate, ID id);

  void detach(T aggregate);

  EntityDiff detectChanges(T aggregate) throws IllegalAccessException;

  void merge(T aggregate);

  T find(ID id);
}

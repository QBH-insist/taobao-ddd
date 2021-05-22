package com.taobao.ddd.second.repository.diff;

public interface AggregateManager<T, ID> {

  /*
    public static <ID extends Identifier, T extends Aggregate<ID>>
        AggregateManager<T, ID> newInstance(Class<T> targetClass) {
      return new AggregateManager<T, ID>(targetClass);
    }
  */

  void attach(T aggregate);

  void attach(T aggregate, ID id);

  void detach(T aggregate);

  EntityDiff detectChanges(T aggregate) throws IllegalAccessException;

  void merge(T aggregate);

  T find(ID id);
}

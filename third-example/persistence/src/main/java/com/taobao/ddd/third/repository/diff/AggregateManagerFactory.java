package com.taobao.ddd.third.repository.diff;

import com.taobao.ddd.third.type.mark.Aggregate;
import com.taobao.ddd.third.type.mark.Identifier;

public class AggregateManagerFactory {

  public static <ID extends Identifier, T extends Aggregate<ID>>
      AggregateManager<T, ID> newInstance(Class<T> targetClass) {
    return new ThreadLocalAggregateManager<T, ID>(targetClass);
  }
}

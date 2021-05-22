package com.taobao.ddd.second.repository.diff;

import com.taobao.ddd.second.type.mark.Aggregate;
import com.taobao.ddd.second.type.mark.Identifier;
import com.taobao.ddd.second.util.DiffUtils;
import com.taobao.ddd.second.util.ReflectionUtils;
import com.taobao.ddd.second.util.SnapshotUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DbContext<T extends Aggregate<ID>, ID extends Identifier> {
  private Class<? extends T> targetClass;
  private Map<ID, T> aggregateMap = new HashMap<>();

  public DbContext(Class<? extends T> targetClass) {
    this.targetClass = targetClass;
  }

  public void attach(T aggregate) {
    if (aggregate.getId() != null) {
      if (!aggregateMap.containsKey(aggregate.getId())) {
        this.merge(aggregate);
      }
    }
  }

  public void detach(T aggregate) {
    if (aggregate.getId() != null) {
      aggregateMap.remove(aggregate.getId());
    }
  }

  public EntityDiff detectChanges(T aggregate) throws IllegalAccessException {
    if (aggregate.getId() == null) {
      return EntityDiff.EMPTY;
    }
    T snapshot = aggregateMap.get(aggregate.getId());
    if (snapshot == null) {
      attach(aggregate);
    }
    return DiffUtils.diff(snapshot, aggregate);
  }

  public T find(ID id) {
    return aggregateMap.get(id);
  }

  public void merge(T aggregate) {
    if (aggregate.getId() != null) {
      T snapshot = null;
      try {
        snapshot = SnapshotUtils.snapshot(aggregate);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      aggregateMap.put(aggregate.getId(), snapshot);
    }
  }

  public void setId(T aggregate, ID id) {
    ReflectionUtils.writeField("id", aggregate, id);
  }
}

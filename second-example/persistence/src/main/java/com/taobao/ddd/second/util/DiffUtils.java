package com.taobao.ddd.second.util;

import com.taobao.ddd.second.repository.diff.EntityDiff;
import com.taobao.ddd.second.repository.diff.SingleDiff;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class DiffUtils {

  public static <T> EntityDiff diff(T snapshot, T object) throws IllegalAccessException {
    final T oldObj = snapshot;
    final T newObj = object;
    Field[] fields = snapshot.getClass().getDeclaredFields();
    EntityDiff diff = new EntityDiff();

    for (Field field : fields) {
      field.setAccessible(true);
      Object oldValue = field.get(oldObj);
      Object newValue = field.get(newObj);
      if (Objects.isNull(oldValue) || Objects.isNull(newValue)) {
        // todo 简单例子暂时不考虑 null 的场景
        continue;
      }
      if (field.getType().equals(List.class)) {
        // todo 处理集合类型的对比
        continue;
      }
      if (!oldValue.toString().equals(newValue.toString())) {
        diff.addDiff(field.getName(), new SingleDiff(oldValue, newValue));
      }
    }

    if (!diff.getDiffs().isEmpty()) {
      diff.setSelfModified(true);
    }

    return diff;
  }
}

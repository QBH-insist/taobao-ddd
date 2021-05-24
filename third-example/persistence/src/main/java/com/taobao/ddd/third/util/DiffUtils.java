package com.taobao.ddd.third.util;

import com.taobao.ddd.third.repository.diff.Diff;
import com.taobao.ddd.third.repository.diff.EntityDiff;
import com.taobao.ddd.third.repository.diff.ListDiff;
import com.taobao.ddd.third.repository.diff.SingleDiff;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiffUtils {

  public static <T> EntityDiff diff(T snapshot, T object) throws IllegalAccessException {
    final T oldObj = snapshot;
    final T newObj = object;
    Field[] fields = snapshot.getClass().getDeclaredFields();
    EntityDiff diff = new EntityDiff();
    Object oldValue, newValue;

    for (Field field : fields) {
      field.setAccessible(true);
      oldValue = field.get(oldObj);
      newValue = field.get(newObj);

      Diff diffAct = doDiff(field.getType(), oldValue, newValue);
      if (!Objects.isNull(diffAct)) {
        diff.addDiff(field.getName(), diffAct);
      }
    }

    if (!diff.getDiffs().isEmpty()) {
      diff.setSelfModified(true);
    }

    return diff;
  }

  private static Diff doDiff(Class<?> type, Object oldObj, Object newObj) {
    if (Objects.isNull(oldObj) || Objects.isNull(newObj)) {
      return null;
    }

    if (type.equals(List.class)) {
      return doListDiff((List) oldObj, (List) newObj);
    }
    if (isDiff(oldObj, newObj)) {
      return new SingleDiff(oldObj, newObj);
    }
    return null;
  }

  private static Diff doListDiff(List<?> oldObj, List<?> newObj) {
    ListDiff diffs = new ListDiff();
    diffs.setOldValue(oldObj);
    diffs.setNewValue(newObj);

    if (oldObj.size() != newObj.size()) {
      diffs.addDiffs(
          newObj.stream()
              .map(newItem -> new SingleDiff(null, newItem))
              .collect(Collectors.toList()));
      return diffs;
    }

    // 假设元素的位置是一一对应的
    for (int i = 0; i < oldObj.size(); i++) {
      Object oldItem = oldObj.get(i);
      Object newItem = newObj.get(i);
      if (isDiff(oldItem, newItem)) {
        diffs.addDiff(new SingleDiff(oldItem, newItem));
      }
    }

    return diffs.size() > 0 ? diffs : null;
  }

  private static boolean isDiff(Object oldObj, Object newObj) {
    if (Objects.isNull(oldObj) || Objects.isNull(newObj)) {
      return false;
    }
    return !oldObj.toString().equals(newObj.toString());
  }
}

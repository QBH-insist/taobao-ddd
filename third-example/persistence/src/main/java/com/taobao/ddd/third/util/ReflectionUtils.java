package com.taobao.ddd.third.util;

import com.taobao.ddd.third.type.mark.Aggregate;
import com.taobao.ddd.third.type.mark.Identifier;

import java.lang.reflect.Field;

public class ReflectionUtils {

  public static <T extends Aggregate<ID>, ID extends Identifier> void writeField(
      String fieldName, T aggregate, ID id) {
    Class<? extends Aggregate> aClass = aggregate.getClass();
    try {
      Field field = aClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(aggregate, id);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }


}

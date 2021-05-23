package com.taobao.ddd.third.repository.diff;

public class SingleDiff implements Diff{
  private Object oldValue;
  private Object newValue;

  public SingleDiff() {}

  public SingleDiff(Object oldValue, Object newValue) {
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public Object getOldValue() {
    return oldValue;
  }

  public Object getNewValue() {
    return newValue;
  }

}

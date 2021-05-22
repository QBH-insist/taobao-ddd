package com.taobao.ddd.second.repository.diff;

import java.util.HashMap;
import java.util.Map;

public class EntityDiff {

  public static final EntityDiff EMPTY = new EntityDiff();

  private boolean isSelfModified;

  private Map<String, Diff> diffByFieldName = new HashMap<>();

  public boolean isEmpty() {
    return false;
  }

  public boolean isSelfModified() {
    return isSelfModified;
  }

  public Diff getDiff(String fieldName) {
    return diffByFieldName.get(fieldName);
  }

  public void setSelfModified(boolean selfModified) {
    isSelfModified = selfModified;
  }

  public void addDiff(String fieldName, SingleDiff diff) {
    this.diffByFieldName.put(fieldName, diff);
  }

  public Map<String, Diff> getDiffs() {
    return diffByFieldName;
  }

}

package com.taobao.ddd.third.repository.diff;

import java.util.*;

public class ListDiff extends AbstractList<Diff> implements Diff {
  private List<Diff> diffs = new ArrayList<>();

  @Override
  public Diff get(int index) {
    return diffs.get(index);
  }

  @Override
  public Iterator<Diff> iterator() {
    return new InnerIterator<>(diffs);
  }

  @Override
  public int size() {
    return diffs.size();
  }

  public void addDiffs(List<Diff> diffs) {
    this.diffs = diffs;
  }

  public void addDiff(Diff diff) {
    this.diffs.add(diff);
  }

  class InnerIterator<E> implements Iterator<E> {
    private int cursor = 0;
    private List<E> duplicate;

    public InnerIterator(List<E> collections) {
      this.duplicate = collections;
    }

    @Override
    public void remove() {
      Iterator.super.remove();
    }

    @Override
    public boolean hasNext() {
      if (cursor < diffs.size()) {
        return true;
      }
      return false;
    }

    @Override
    public E next() {
      return duplicate.get(cursor++);
    }
  }
}

package com.taobao.ddd.second.repository.diff;

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
    return 0;
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

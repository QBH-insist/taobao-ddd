package com.taobao.ddd.third.repository.diff;

/** mark 接口 */
public interface Diff {

  Object getOldValue();

  Object getNewValue();
}

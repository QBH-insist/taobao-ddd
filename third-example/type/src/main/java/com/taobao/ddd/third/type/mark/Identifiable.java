package com.taobao.ddd.third.type.mark;

import java.io.Serializable;

public interface Identifiable<ID extends Identifier> extends Serializable {
  ID getId();
}

package com.taobao.ddd.second.type.mark;

import java.io.Serializable;

public interface Identifiable<ID extends Identifier> extends Serializable {
  ID getId();
}

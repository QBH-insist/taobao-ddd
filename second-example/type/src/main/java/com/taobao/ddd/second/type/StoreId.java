package com.taobao.ddd.second.type;

import com.taobao.ddd.second.type.mark.Identifier;

public class StoreId implements Identifier<Long> {
  public Long id;

  @Override
  public Long getValue() {
    return id;
  }

}

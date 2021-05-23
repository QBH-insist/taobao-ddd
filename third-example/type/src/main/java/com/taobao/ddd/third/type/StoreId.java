package com.taobao.ddd.third.type;

import com.taobao.ddd.third.type.mark.Identifier;

public class StoreId implements Identifier<Long> {
  public Long id;

  @Override
  public Long getValue() {
    return id;
  }

}

package com.taobao.ddd.third.persistence.converter;

import com.taobao.ddd.third.domain.entity.LineItem;
import com.taobao.ddd.third.persistence.LineItemDO;

public class LineItemDataConverter {

  public static final LineItemDataConverter INSTANCE = new LineItemDataConverter();

  public LineItemDO toData(LineItem lineItem) {
    return null;
  }

  public LineItem fromData(LineItemDO lineItemDO) {
    return null;
  }

}

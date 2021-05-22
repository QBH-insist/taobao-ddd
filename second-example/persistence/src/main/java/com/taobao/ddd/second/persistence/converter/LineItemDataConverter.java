package com.taobao.ddd.second.persistence.converter;

import com.taobao.ddd.second.domain.entity.LineItem;
import com.taobao.ddd.second.persistence.LineItemDO;
import com.taobao.ddd.second.type.OrderId;

public class LineItemDataConverter {

  public static final LineItemDataConverter INSTANCE = new LineItemDataConverter();

  public LineItemDO toData(LineItem lineItem) {
    return null;
  }

  public LineItem fromData(LineItemDO lineItemDO) {
    return null;
  }

}

package com.taobao.ddd.third.persistence.dao;

import com.taobao.ddd.third.persistence.LineItemDO;

public interface LineItemDAO {

  int update(LineItemDO lineItemDO);

  int insert(LineItemDO lineItemDO);

}

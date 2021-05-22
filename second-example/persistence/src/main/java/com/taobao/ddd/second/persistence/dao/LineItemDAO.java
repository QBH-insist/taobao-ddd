package com.taobao.ddd.second.persistence.dao;

import com.taobao.ddd.second.persistence.LineItemDO;

public interface LineItemDAO {

  int update(LineItemDO lineItemDO);

  int insert(LineItemDO lineItemDO);

}

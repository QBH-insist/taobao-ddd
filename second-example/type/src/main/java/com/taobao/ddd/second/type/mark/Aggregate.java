package com.taobao.ddd.second.type.mark;

/** 聚合根的Marker接口 */
public interface Aggregate<ID extends Identifier> extends Entity<ID> {

}

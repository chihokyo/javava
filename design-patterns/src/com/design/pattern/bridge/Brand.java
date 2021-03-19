package com.design.pattern.bridge;
/**
 * 桥接模式
 * 品牌接口
 */
public interface Brand {
    // 每个品牌都有开机关机打电话功能
    void open();
    void close();
    void call();
}

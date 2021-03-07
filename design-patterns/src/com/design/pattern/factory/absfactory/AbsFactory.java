package com.design.pattern.factory.absfactory;

/**
 *　抽象工厂模式的抽象层 （接口）
 */
public interface AbsFactory {
    // 具体类来实现
    public Pizza createPizza(String orderType);
}

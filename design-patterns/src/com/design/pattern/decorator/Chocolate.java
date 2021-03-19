package com.design.pattern.decorator;
/**
 * 装饰类
 * Chocolate
 */
public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        // 描述 这里都是直接用的父类的
        setDesc("Chocolate"); 
        // 价格 这里都是直接用的父类的
        setPrice(2.0f);
    }
}

package com.design.pattern.decorator;
/**
 * 装饰类
 * Soy
 */
public class Soy extends Decorator {
    public Soy(Drink drink) {
        super(drink);
        // 描述 这里都是直接用的父类的
        setDesc("Soy"); 
        // 价格 这里都是直接用的父类的
        setPrice(2.0f);
    }
}

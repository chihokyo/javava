package com.design.pattern.decorator;
/**
 * 被装饰类
 * 特浓咖啡 Espresso
 */
public class Espresso extends Coffee {

    public Espresso() {
        setDesc("Espresso");
        setPrice(20.0f);
    }
}

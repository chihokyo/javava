package com.design.pattern.decorator;

/**
 * 咖啡类
 * 继承了Drink
 */
public class Coffee extends Drink{

    @Override
    public float cost() {
        return super.getPrice();
    }
}

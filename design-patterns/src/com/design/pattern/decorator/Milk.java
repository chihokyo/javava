package com.design.pattern.decorator;
/**
 * 被装饰类
 * 牛奶
 * 因为被装饰类和装饰类是一种组合关系
 * 必须点咖啡才能有牛奶
 * 所以对象的构造器必须传入一个实例对象
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        // 描述 这里都是直接用的父类的
        setDesc("Milk"); 
        // 价格 这里都是直接用的父类的
        setPrice(1.0f);
    }
    
}

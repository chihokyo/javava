package com.design.pattern.decorator;

/**
 * 抽象类 这个类是被装饰的
 * Drink
 */
public abstract class Drink {

    // 用于对饮品的描述
    private String desc;
    // 用于计算饮品价格
    private float price = 0.0f;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // 计算费用的抽象方法
    // 通过子类实现
    public abstract float cost();

}

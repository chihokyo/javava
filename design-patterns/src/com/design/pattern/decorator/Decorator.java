package com.design.pattern.decorator;

/**
 * 装饰类 Decorator 
 * 用于装饰其他类
 */
public class Decorator extends Drink {

    private Drink drink;

    /**
     * 组合的改变 新建一个装饰，那就必须传入一个实例对象（主饮品） 
     * 比如珍珠奶茶即使加珍珠，也需要有个奶茶吧
     * 
     * @param drink 饮品实例对象
     */
    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        // 获取父类(奶茶)的价格+自己的价格（珍珠）
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        // super.getDesc() 装饰描述 加料
        // super.getPrice() 装饰价格 加料价格
        // drink.getDesc() 主要饮品的描述 这个其实是一个递归（本身也包裹了一层）
        return "配料: " + super.getDesc() + "价格: "
            + super.getPrice()+ ", === " + drink.getDesc();
    }
}

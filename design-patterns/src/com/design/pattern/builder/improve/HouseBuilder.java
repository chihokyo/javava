package com.design.pattern.builder.improve;

/**
 * 抽象产品的建造 
 * 定义一些方法在里面
 */
public abstract class HouseBuilder {

    // 初始化一个房子
    protected House house = new House();

    // 抽象方法 这是流程
    public abstract void buildBasic();

    public abstract void buildWall();

    public abstract void roofed();

    // 返回一个房子
    public House buildHouse() {
        return house;
    }

}

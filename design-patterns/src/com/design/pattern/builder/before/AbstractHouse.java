package com.design.pattern.builder.before;

/**
 * 建造者模式进化前
 * 抽象基类
 */
public abstract class AbstractHouse {
    
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}

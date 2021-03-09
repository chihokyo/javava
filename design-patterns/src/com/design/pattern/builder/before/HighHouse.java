package com.design.pattern.builder.before;

/**
 * 建造者模式进化前 
 * 具体实现 HighHouse
 */
public class HighHouse extends AbstractHouse {

    @Override
    public void buildBasic() {
        System.out.println("HighHouse buildBasic...");
    }

    @Override
    public void buildWalls() {
        System.out.println("HighHouse buildBasic...");
    }

    @Override
    public void roofed() {
        System.out.println("HighHouse buildBasic...");
    }

}

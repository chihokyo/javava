package com.design.pattern.builder.improve;

/**
 * 指挥者
 * 通过 houseBuilder 里面的各种方法
 * 组合指挥如何进行建造一个房子 constructHouse
 * 这里
 */
public class HouseDirector {

    HouseBuilder houseBuilder = null;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    } 

    // 和上面2选1 1个set 一个构造器
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }
    
    // 建造方法
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWall();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }

}

package com.design.pattern.builder.improve;

/**
 * 高楼
 */
public class HighBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println("HighBuilding buildBasic....");

    }

    @Override
    public void buildWall() {
        System.out.println("HighBuilding buildWall....");

    }

    @Override
    public void roofed() {
        System.out.println("HighBuilding roofed....");
    }

}

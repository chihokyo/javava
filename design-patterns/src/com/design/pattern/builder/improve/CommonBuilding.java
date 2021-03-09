package com.design.pattern.builder.improve;

/**
 * 普通房
 */
public class CommonBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println("CommonBuilding buildBasic....");

    }

    @Override
    public void buildWall() {
        System.out.println("CommonBuilding buildWall....");

    }

    @Override
    public void roofed() {
        System.out.println("CommonBuilding roofed....");
    }

}

package com.design.pattern.builder.improve;

public class ClientMain {
    public static void main(String[] args) {

        System.out.println("建造一个普通房子");
        CommonBuilding commonBuilding = new CommonBuilding();
        // 构造器建造 放进去指挥者
        HouseDirector houseDirector = new HouseDirector(commonBuilding);
        House house = houseDirector.constructHouse();

        System.out.println(house.toString());
        System.out.println("建造一个高楼");

        HighBuilding highBuilding = new HighBuilding();
        // set方法建造 指挥者
        houseDirector.setHouseBuilder(highBuilding);
        houseDirector.constructHouse();

    }
}

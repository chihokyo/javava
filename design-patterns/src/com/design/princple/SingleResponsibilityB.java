package com.design.princple;

/**
 * 单一职责 方案B
 */
public class SingleResponsibilityB {
    public static void main(String[] args) {

        RoadVehicle rv = new RoadVehicle();
        AirVehicle av = new AirVehicle();
        SeaVehicle sv = new SeaVehicle();
        rv.run("Gaotie");
        av.run("ANA");
        sv.run("Hexie");
    }
}

/**
 * 遵守了单一职责原则 
 * 该谁的活儿哪个类就去干 → 分解类
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " RoadVehicle run....");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " AirVehicle run....");
    }
}

class SeaVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " SeaVehicle run....");
    }
}

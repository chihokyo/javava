package com.design.princple;

/**
 * 单一职责 方案A
 */
public class SingleResponsibilityA {
    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        // 这样不行的，这么多交通工具是要拆分的
        // 毕竟单一职责，一个方法怎么可以干这么多
        v.run("Plane");
        v.run("Tesla");
        v.run("Train");
    }
}

/**
 * 假设写一个交通工具类 有飞机，火车，汽车，走路
 * 下面就不行了，因为你发现只能在公路跑
 */
class Vehicle {
    public void run(String vehicle){
        System.out.println(vehicle + " Road run....");
    }
}

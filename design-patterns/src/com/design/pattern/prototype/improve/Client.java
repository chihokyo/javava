package com.design.pattern.prototype.improve;

public class Client {
    public static void main(String[] args) {

        Sheep sheep = new Sheep("Amy", 2, "black");

        /******** 这个时候在Sheep里增加一个对象属性 */
        sheep.friend = new Sheep("Andy", 3, "yellow");

        Sheep sheep2 = (Sheep) sheep.clone(); // 克隆
        Sheep sheep3 = (Sheep) sheep.clone(); // 克隆
        Sheep sheep4 = (Sheep) sheep.clone(); // 克隆
        Sheep sheep5 = (Sheep) sheep.clone(); // 克隆

        System.out.println("******使用克隆模式******");
        System.out.println(sheep);
        System.out.println(sheep2);
        System.out.println(sheep3);
        System.out.println(sheep4);
        System.out.println(sheep5);

        // 这个时候如果在增加一个属性的话，使用克隆就比较方便了。
        // 不用再像上次的getXXX() 这样

        System.out.println(sheep2.friend.hashCode()); // 789451787
        System.out.println(sheep3.friend.hashCode()); // 789451787
        System.out.println(sheep3.friend.hashCode()); // 789451787

    }
}

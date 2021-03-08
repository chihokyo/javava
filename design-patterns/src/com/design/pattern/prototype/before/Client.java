package com.design.pattern.prototype.before;

public class Client {
    public static void main(String[] args) {

        Sheep sheep = new Sheep("tom", 1, "white");

        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        Sheep sheep5 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        // ...

        System.out.println(sheep); // { name='tom', age='1', color='white'}
        System.out.println(sheep2); // { name='tom', age='1', color='white'}
        System.out.println(sheep3); // { name='tom', age='1', color='white'}
        System.out.println(sheep4); // { name='tom', age='1', color='white'}
        System.out.println(sheep5); // { name='tom', age='1', color='white'}
    }
}

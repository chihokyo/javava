package com.polymorphism.exer;

public class Woman extends Person {
    boolean isCute;

    public void beBeauty() {
        System.out.println("Woman Beauty Girl");
    }

    @Override
    public void eat() {
        System.out.println("Woman eat good");
    }

    @Override
    public void walk() {
        System.out.println("Woman walk fast");
    }

}

package com.polymorphism.exer;

public class Man extends Person {
    boolean isSmoking;
    int id = 200;

    public void fight() {
        System.out.println("Man can fight");
    }

    @Override
    public void eat() {
        System.out.println("Man eat more");
    }

    @Override
    public void walk() {
        System.out.println("Man walk slow");
    }
}

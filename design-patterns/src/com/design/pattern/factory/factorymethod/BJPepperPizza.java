package com.design.pattern.factory.factorymethod;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJPepperPizza");
        System.out.println("BJPepperPizza prepare...");
    }
}
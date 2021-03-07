package com.design.pattern.factory.absfactory;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJPepperPizza");
        System.out.println("BJPepperPizza prepare...");
    }
}
package com.design.pattern.factory.simplefactory;
/**
 * 胡椒类(继承披萨)
 */
public class PepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("PepperPizza prepare...");
    }
}

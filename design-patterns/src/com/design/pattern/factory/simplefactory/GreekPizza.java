package com.design.pattern.factory.simplefactory;

/**
 * 希腊类(继承披萨)
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("GreekPizza prepare...");
    }
}

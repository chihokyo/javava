package com.design.pattern.visitor;
/**
 * 访问者模式
 * 继承抽象类这里的抽象类是评价
 * 具体进行评价
 */
public class Bad extends Action {

    @Override
    public void getManResult(Man man) {
        System.out.println("Man Bad");
        
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("Woman Bad");
        
    }
    
}

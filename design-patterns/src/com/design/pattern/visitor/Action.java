package com.design.pattern.visitor;
/**
 * 访问者模式
 * 这里的抽象类是评价
 */
public abstract class Action {

    public abstract void getManResult(Man man);

    public abstract void getWomanResult(Woman woman);
    
}

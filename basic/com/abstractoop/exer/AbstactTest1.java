package com.abstractoop.exer;
/**
 * abstract 使用注意点
 * 
 * 1 不能修饰：属性，构造器。但抽象类内里可以定义构造器
 * 
 * 2 不能修饰私有方法
 *      abstract 不能修饰这些关键字 private(子类根本看不到) static(一个方法不能是static才能被重写) final 
 *      因为需要重写，需要子类继承，不可以重写和继承的，都不能抽象起来。
 */
public class AbstactTest1 {
    
}

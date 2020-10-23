package com.objtest.exer;
/**
 * 关于object类【java.lang.object】
 *  1 Object 是所有java类的根父类
 *  2 如果类的声明没有指定 extends 什么类。 那么默认就是 继承了 java.lang.Object 类
 *  3 Object 类中的功能(属性or方法 )就具有通用性
 *  4 Object 类 只声明了一个空参构造器
 * equals()/toString()/getClass()/hasCode()/clone()/finalize()/wait()/notify()/notifyAll()
 */
public class ObjectTest {
    public static void main(String[] args) {
        Order order = new Order();
        System.out.println(order.getClass()); // class com.objtest.exer.Order    
        System.out.println(order.getClass().getSuperclass()); // class java.lang.Object
    }
}

class Order {

}
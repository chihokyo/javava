package com.codeblock.exer;
/*
 * 对属性可以赋值的位置：
 * ①默认初始化
 * ②显式初始化/⑤在代码块中赋值 顺序要看先写后写，一般是先声明，在代码块
 * ③构造器中初始化
 * ④有了对象以后，可以通过"对象.属性"或"对象.方法"的方式，进行赋值
 * 
 * 
 * 执行的先后顺序：① - ② / ⑤ - ③ - ④
 */
public class Order {
    public static void main(String[] args) {
        OrderTest o1 = new OrderTest();
        System.out.println(o1);
    }
}

class OrderTest {

    {
        orderId = 4;
    }

    int orderId = 3;
}
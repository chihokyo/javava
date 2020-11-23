package com.jdk8.exer;

import java.util.Comparator;

/**
 * Lambda表达式
 * 吐槽一下，这个感觉在好多语言都看到了。
 * 第一次见到是在Python
 */
public class LambdaTest1 {
    public static void main(String[] args) {

        System.out.println("***使用Lambda之前***");
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                System.out.println("这里是一个匿名接口 Runnable r1");
            }
        };
        r1.run();

        System.out.println("***使用Lambda之后***");
        Runnable r2 = () -> System.err.println("这里是一个匿名接口 Runnable r2");
        r2.run();


        // 带参数的
        System.out.println("***使用Lambda之前***");
        Comparator<Integer> com1 = new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
               return Integer.compare(o1, o2);
            };
        };
        int isCompare1 = com1.compare(12, 100);
        System.out.println(isCompare1); // -1

        System.out.println("***使用Lambda之后***");
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int isCompare2 = com2.compare(100, 8);
        System.out.println(isCompare2); // 1

        System.out.println("***使用方法引用之后***");
        Comparator<Integer> com3 = Integer :: compare;
        int isCompare3 = com3.compare(100,200);
        System.out.println(isCompare3); // -1
        
    }
}


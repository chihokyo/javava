package com.errorexception.exer;
/**
 * 关于 Error
 *  1 首先要明白 Error 和 Exception 是2个不同的概念
 *      一般 Java虚拟机无法解决的严重问题。如果JVM系统内部错误，资源耗尽的情况。
 *      如 StackOverflowError 和 ODM 这样
 * 
 *  一般不会编写针对性的代码进行处理
 */
public class ErrorTest {
    public static void main(String[] args) {
        
        // 1 栈溢出 : java.lang.StackOverflowError
        // main(args);

        // 2 堆溢出 : java.lang.OutOfMemoryError
        // Integer[] arr = new Integer[1024*1024*1024];
    }
}

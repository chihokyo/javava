package com.stringclass.com;
/**
 * 什么是序列化 Serializable ？
 * Serializable 就是一种流。字节的方式，对象本身是不可以传递的，但是可以序列化成为一个字节流就是可以传输的
 * 
 * 什么是Comparable？
 * 就是可以对比大小
 * 
 * 什么是 private final byte[] value; ？
 * 代表不可变的字符序列 简称 不可变性
 * 
 */
public class StringTest {
    public static void main(String[] args) {
        // 看源码
        // 实现了一些接口
        // implements java.io.Serializable, Comparable<String>, CharSequence,
        //        Constable, ConstantDesc {
        // 是一个不可变的byte数组 用于存储字符串数据
        // private final byte[] value; 
        
        /************ 字面量赋值，因为一般string作为一个类都需要new一下。*********** */
        String s1 = "abc";
        String s2 = "abc";

        /******地址一致，方法区内有字符串常量池。字符串常量池不会存储2个相同的字符串，所以可以复用*********** */
        System.out.println(s1 == s2); // true
        
        /******直接修改，地址也会变*********** */
        s1 = "hello"; // 这个时候常量池会有一个新的地区 hello ox111 s1指向了新的地址，也就是不可变性。
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2); // false
        System.out.println(s1.equals(s2)); // false


        /****这个时候会在方法区的字符串常量池里新建一个 abcbbb 指向 s3 而s2本身abc是不变的***/
        String s3 = "abc";
        s3 += "bbb";
        System.out.println(s3); // abcbbb
        
        /******这里也是重新开辟的mbc区域 这也恰巧证明了String 是 不可变字符区间*******/
        String s4 = "abc";
        String s5 = s4.replace("a", "m");
        System.out.println(s4); // abc
        System.out.println(s5); // mbc

    }
}

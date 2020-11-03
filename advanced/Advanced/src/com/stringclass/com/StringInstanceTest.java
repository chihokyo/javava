package com.stringclass.com;
/**
 * String实例化的2种方式
 *  1 通过字面量进行定义 String str = "hello";
 *  2 通过new + 构造器方式进行 String s1 = new String(); 等等
 * 
 * 
 * 【Q&A】
 * String s1 = new String("AB");这里是几个对象
 * 答案：总共俩
 * 1 一个是 堆空间中的 new的 string
 * 2 一个是 AB 所在的 char[]数组所在的常量池的对象数据 "AB"
 */
public class StringInstanceTest {
    public static void main(String[] args) {

        String s1 = "JAVAEE";
        String s2 = "JAVAEE";

        String s3 = new String("JAVAEE");
        String s4 = new String("JAVAEE");

        System.out.println(s1 == s2); // true 方法区字符串常量池 地址一样
        System.out.println(s1 == s3); // false 一个是heap堆空间对象地址 一个是常量池地址 地址不一样
        System.out.println(s1 == s4); // false 同上
        System.out.println(s3 == s4); // false 新的new对象 2个对象地址

        System.out.println(s1.equals(s2)); // true equals() 内容一样就可以了
        System.out.println(s1.equals(s3)); // true
        System.out.println(s1.equals(s4)); // true
        System.out.println(s3.equals(s4)); // true

        // 解说，差不多就是什么呢。
        // String s4 = new String("JAVAEE"); 堆空间里有一个 String 对象的 value 属性值
        // value是一个引用数据类型
        // value属性值的地址就是 指向了 字符串常量池地址

        System.out.println("*******华丽的分割线********");

        Person p1 = new Person("Tom", 20);
        Person p2 = new Person("Tom", 20);
        System.out.println(p1 == p2); // false 地址不一样
        System.out.println(p1.equals(p2)); // false 地址不一样

        System.out.println(p1.name.equals(p2.name)); // true 结果一样
        System.out.println(p1.name == p2.name); // true 地址一样 因为这里的Tom通过构造器赋值的时候，用的字面量，比较地址值

        p1.name = "Jerry";
        System.out.println(p1.name.equals(p2.name)); // false 结果都不一样了
        System.out.println(p1.name == p2.name); // false 指向的地址不一样

        System.out.println("*******华丽的分割线********");

        String ss1 = "javascript"; // 字面量
        String ss2 = "Hadoop"; // 字面量
        String ss3 = "javascriptHadoop"; // 字面量连接
        String ss4 = "javascript" + "Hadoop"; // 字面量连接
        String ss5 = ss1 + "Hadoop"; // 只要有其他变量名参与了，都不是在常量池了，而在堆空间当中。类似于new了一个新对象
        String ss6 = "javascript" + ss2;
        String ss7 = ss1 + ss2;

        /**
         * 结论
         * 1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
         * 2.只要其中有一个是变量，结果就在堆中。
         * 3.如果拼接的结果调用intern()方法，返回值就在常量池中
         */

        String ss8 = ss5.intern();
        System.out.println(ss3 == ss8); // true 返回值得到的ss8使用的常量值中已经存在的“javaEEhadoop”
        
        System.out.println(ss3 == ss4); // true 地址一样
        System.out.println(ss3 == ss5); // false 因为地址问题
        System.out.println(ss3 == ss6); // false
        System.out.println(ss5 == ss6); // false
        System.out.println(ss5 == ss7); // false

    }
}

class Person {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
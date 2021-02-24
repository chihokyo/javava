package com.enumclass.exer;

import java.util.ArrayList;

/**
 * 注解jdk5.0开始的增加了对MetaData的新增功能
 *
 * 1. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。通过使用 Annotation,
 * 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android
 * 中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗
 * 代码和XML配置等。
 *
 * 2. Annocation的使用示例
 * 示例一：生成文档相关的注解
 * 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 *  @Override: 限定重写父类方法, 该注解只能用于方法
 *  @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 * 
 *  更新换代的时候为了向下适应，所以就 Deprecated 提醒你这个过时了。
 * 不影响开发，但存在更好的选择。但是会提醒你。
 * 
 *  @SuppressWarnings: 抑制编译器警告
 * 示例三：跟踪代码依赖性，实现替代配置文件功能
 */
public class AnnotationTest {
    public static void main(String[] args) {
        // 声明未使用就会编译器就会出现提示，这样可以抑制编译器
        @SuppressWarnings("unused")
        int num = 10;

        // 一个抑制未使用，一个抑制泛型
        // 为什么可以写多个
        // 因为看源码这是一个数组结构
        // String[] value();
        @SuppressWarnings({"unused", "rawtypes"})
        ArrayList list = new  ArrayList();
    }
}

class Person {

    private String name;
    private int age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("Person walk");
    }
}

interface PersonInfo {
    void show();
}

class Student extends Person implements PersonInfo {
    // 如果不写这个注解，下面的重写方法 walk 如果不小心写成了 wa1k 这样就能给校验出来了
    // 不写编译器就无法校验出来
    // 只有方法才有 Override 构造器不行
    @Override
    public void walk() {
        super.walk();
        System.out.println("Student walk");
    }

    @Override
    public void show() {
        System.out.println("Student show");
    }
}
package com.inheritance.exer;
/**
 * 面向对象特征之二 继承性（Inheritance）
 * 
 * 好处
 *      1 减少代码的冗余，提高了复用性
 *      2 便于功能的扩展 
 *      3 为了之后的【多态性】提供前提
 *      4 不仅仅可以解决多个类似的集成1个，也可以换一个思维，那就是共通的属性or方法进行集成
 * 
 * 格式
 * 
 *  class A extends B{}
 *      A subclass 子类
 *      B superclass 父类
 * 
 *          1 一旦A继承B，那么A就获取了B的结构，所有的属性or方法（包括私有private） 【构造器并不行】
 *          虽然继承了private 但是因为封装性的影响，子类无法直接父类结构调用，只能通过方法调用
 *          继承到 和 调用与否 是2个问题
 * 
 *          2 子类继承之后还可以定义自己特有的属性or方法，实现拓展
 *          3 单继承性：没有类的多重继承，只能单继承。
 *          4 但是有 多层继承，（A→B→C）一个子类可以继承父类，父类在继承其他父类。子类也只是相对的概念
 *          5 间接父类就是这么来的
 * 没有显示的声明一个类的父类，那么这个类就是继承与 java.lang.Object 所有类都直接or间接继承与这个类
 */ 
public class ExtendTest {
    
    public static void main(String[] args) {
        
        Person p1 = new Person();
        // p1.age = 19;
        p1.eat();

        Student s1 = new Student();
        // s1.age = 90;
        s1.eat();
        s1.sleep();
        s1.study();
        s1.setAge(100);
        System.out.println(s1.getAge()); // 100 证明私有属性可以获取到了，但是无法直接[this.age这样]直接调用。
    }
}

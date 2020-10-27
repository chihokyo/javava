package com.jdk8.exer;
/**
 * JDK8之前接口
 * 只能定义  1 全局常量 2 抽象方法
 * 
 * JDK8之后
 * 新增 1 静态方法 2 默认方法
 * 所以就很麻烦了。
 */
public interface Compare {

    public static void main(String[] args) {
        SubCompare s = new SubCompare();
        // 1 接口中的静态方法，只能通过接口来调用
        // s.methodA(); 未定义 The method methodA() is undefined for the type SubCompare

        CompareA.methodA(); // CompareA static void methodA

        // 2 通过实现类的对象，可以调用接口中的默认方法
        // 如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写以后的方法

        s.methodB(); // SubCompare void methodB

        // 3 类优先原则，如果子类继承父类 & 实现接口都 & 子类没有重写方法

        // 4 如果实现类实现了多个接口，而多个接口定义了 【同名同参数默认方法】
        // 那么子类没有重写的情况下，会报错。接口冲突，这时候就必须要写一个子类重写方法
        // 女朋友和妈妈一起掉进水里。必须要做出选择的时候

        s.methodC(); // SubCompare void methodC

        s.myMethod();

        // SubCompare void methodC
        // superClass default void methodC
        // CompareA default void methodC
        // CompareB default void methodC
    }
}

class SubCompare extends SuperClass implements CompareA, CompareB {
    // 因为没有抽象方法，所以没有要重写的方法
    @Override
    public void methodB() {
        System.out.println("SubCompare void methodB");
    }

    public void methodC() {
        System.out.println("SubCompare void methodC");
    }

    // 5 如何在子类（or实现类）中调用父类，被重写的方法
    public void myMethod() {
        methodC(); // 调用自己定义的重写方法
        super.methodC(); // 调用父类中声明的重写方法
        // 调用接口中声明的默认方法
        CompareA.super.methodC(); // 规定写法
        CompareB.super.methodC();
    }
}

interface CompareA {
    // 静态方法
    public static void methodA() {
        System.out.println("CompareA static void methodA");
    }

    // 默认方法
    public default void methodB() {
        System.out.println("CompareA default void methodB");
    }

    default void methodC() {
        System.out.println("CompareA default void methodC");
    }
}

interface CompareB {
    default void methodC() {
        System.out.println("CompareB default void methodC");
    }
}

class SuperClass {
    public void methodC() {
        System.out.println("SuperClass default void methodC");
    }
}
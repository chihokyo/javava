package com.jdk9.exer;
/**
 * JDK9新特性
 * 1 模块化系统更新
 * 2 新增REPL（Read evaluate print loop）交互式编程环境工具
 * 3 接口私有化
 */
public class JDKTest1 {
    public static void main(String[] args) {
        // 1 模块化更新 module-info.java
        // 导出 控制那些包可以被访问到
        // module JDKTest1 {
        //     exports com.jdk8.exer;
        // }
        // 导入
        // module JDKTest1 {
        //     require JDKTest1;
        // }
        
        // 3 接口私有化
        // 原来的接口是无法定义private方法的
        // 接口中的静态方法只能自己调用
        MyInterfaceJDK9.staticMethodJDK9();

        // 接口中实现类不能调用静态方法
        // jdk9Class.staticMethodJDK9();

        jdk9Class j9 = new jdk9Class();
        j9.defaultMethodJDK9();
        
        // 接口中的私有方法，不能在外部调用
        // j9.privateMethodJDK9();

    }
}

interface MyInterfaceJDK9 {

    // 虽然前三个没有写，但是权限修饰符都是public 
    void methodJDK9();

    static void staticMethodJDK9(){
        System.out.println("jdk接口中静态方法 staticMethodJDK9");
    }

    default void defaultMethodJDK9() {
        System.out.println("jdk接口中默认方法 defaultMethodJDK9");
        privateMethodJDK9();
    }

    // jdk9开始允许定义的
    private void privateMethodJDK9(){
        System.out.println("jdk接口中私有方法 privateMethodJDK9");
    }
}


class jdk9Class implements MyInterfaceJDK9 {
    @Override
    public void methodJDK9() {
        System.out.println("Override 重写的 methodJDK9");
    }

    @Override
    public void defaultMethodJDK9() {
        System.out.println("Override 重写的 defaultMethodJDK9");
    }

}
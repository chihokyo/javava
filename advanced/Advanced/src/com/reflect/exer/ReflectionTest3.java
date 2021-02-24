package com.reflect.exer;

import java.lang.annotation.ElementType;

/**
 * 1. 类的实例
 * 只要是类结构，都可以拥有 
 * Class 实例 外部类 内部类 匿名类 接口interface 数组[] 枚举enum 注解annotation 基本数据类型
 * void 都是的
 * 
 * 2.类加载器
 *  引导类加载器
 *  拓展类加载器
 *  系统类加载器
 */
public class ReflectionTest3 {
    public static void main(String[] args) {
        
        // 看看我的实例们
        Class<?> c1 = Object.class;
        Class<?> c2 = Comparable.class;
        Class<?> c3 = String[].class;
        Class<?> c4 = int[][].class;
        Class<?> c5 = ElementType.class; // 枚举类
        Class<?> c6 = Override.class; // 注解
        Class<?> c7 = int.class;
        Class<?> c8 = void.class;
        Class<?> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<?> c10 = a.getClass();
        Class<?> c11 = a.getClass();
        // 只要数组的元素类型和维度一样，那么就是同一个 Class
        System.out.println(c10 == c11);

        // 关于类的加载过程
        // 1 load 2 link 3 init
        // 第一步，把 Class A 加载到内存中 就已经有了实例
        // 第二步， link 就是把静态变量m初始化一下 m=0
        // 第三步，初始化之后，m的值由 clinit 方法执行决定
            // 这个A的类构造器 clinit方法 由 类变量的赋值和静态代码块中的语句按照顺序合并
            // 相当于
            // <clinit>(){
            //     m = 300;
            //     m = 100;
            // }

        // 类加载器作用。就是把字节码文件加载到内存里
        // 并且把静态数据转换成方法区运行时的数据结构
        // 然后在堆里生成一个代表这个类的java.lang.Class对象
        // 作为数据的访问入口
        System.out.println(A.m); // 100

        B bInstance = new B();
        bInstance.printLoader();
        
    }
}


class A {
    static {
        m = 300;
    }
    static int m = 100;
}

class B {
    public void printLoader() {
         // 关于类加载器
        //  对于自定义类，使用系统类加载器进行加载
        //  调用系统类加载的getParent() 获取扩展类加载器
        //  调用扩展类加载的getParent() 无法获取引导类加载器
        // 引导类加载器主要负责加载java核心类库，无法加载自定义类
         ClassLoader cl = ReflectionTest3.class.getClassLoader();
         System.out.println(cl); // jdk.internal.loader.ClassLoaders$AppClassLoader@55054057

        ClassLoader cl2 =  cl.getParent();
        System.out.println(cl2); //jdk.internal.loader.ClassLoaders$PlatformClassLoader@7440e464

        ClassLoader cl3 = cl2.getParent();
        System.out.println(cl3); // null

        ClassLoader stringLoader = String.class.getClassLoader();
        System.out.println(stringLoader); // null 引导类加载器 核心库
    }
}
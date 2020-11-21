package com.reflect.exer;

import java.lang.reflect.*;

/**
 * 获取各种指定的结构
 * 
 * 前面写的大多都是全部的结构 是 数组类型的 现在写的都是指定的一些结构
 * 具体到了某一个结构
 * 
 * 1 属性
 * 2 对象
 * 3 构造器
 */
public class ReflectionTest8 {
    public static void main(String[] args) {

        Class<Animal> clazz = Animal.class;

        try {
            // 通过构造器新建一个Animal对象
            Animal a = clazz.getDeclaredConstructor().newInstance();
            // Animal 有 name id age 这些属性，通过getField()获取具体的属性
            // 1.获取属性对象 要求运行时类中的属性声明必须要为public
            Field id = clazz.getField("id");
            // Field name = clazz.getField("name");
            System.out.println(id);
            // System.out.println(name); // private权限无法获取
            // 2.设置当前属性的值
            id.set(a, 100);
            // 3 获取
            int pid = (int) id.get(a);
            System.out.println(pid); // 100

        
            System.out.println("*****获取运行时类指定的属性*******");

            // 1. 获取指定的某个属性 Field getDeclaredField(String name)
            Field name = clazz.getDeclaredField("name");
            // 2. 保证当前属性可以进行访问
            name.setAccessible(true);
            // 3 设置，获取当前属性
            name.set(a, "Lee");
            System.out.println(name.get(a)); // Lee

            System.out.println("*****获取运行时类指定的方法*******");

            //  参数1 方法名 参数2 参数列表 是一个形参列表 为什么是数组，因为参数可以不只是一个
            // getMethod(String name, Class<?>... parameterTypes)
            Method descInfo = clazz.getMethod("descInfo", String.class);
            descInfo.setAccessible(true);
            // invoke 参数1 方法调用者 参数2 给方法形参赋值的实参
            Object returnVal = descInfo.invoke(a, "这里是一段对于Animal的描述");
            System.out.println(returnVal); // 这里是一段对于Animal的描述
            
            // ？如果是一个静态方法呢
            // 如果运行时的方法没有返回值呢，默认就是null

            Method staticMethod = clazz.getMethod("staticMethod");
            staticMethod.setAccessible(true);
            Object returnValSta = staticMethod.invoke(a); // 这是Animal的静态方法staticMethod()
            System.out.println(returnValSta); // null

            System.out.println("*****获取运行时类指定的构造器*******");
            
            // 1.获取指定的构造器 getDeclaredConstructor():参数：指明构造器的参数列表
            Constructor<?> cons = clazz.getConstructor(String.class);
            //2.保证此构造器是可访问的
            cons.setAccessible(true);
            Animal a2 = (Animal) cons.newInstance("TOM");
            // 重写 toString方法之前
            System.out.println(a2); // com.reflect.exer.Animal@5451c3a8
            // 重写 toString方法之后
            System.out.println(a2); // { name='TOM', age='0', id='0'}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }
}



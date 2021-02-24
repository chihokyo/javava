package com.reflect.exer;

import java.lang.reflect.*;

/**
 * 获取运行时类的构造器 & 父类
 */
public class ReflectionTest7 {
    public static void main(String[] args) {

        Class<Animal> clazz = Animal.class;

        // 1 getConstructors() 获取当前运行时类的所有public构造器 不包含父类
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println(c);
        }
        // 这里输出的并不包含父类的构造器
        // public com.reflect.exer.Animal()
        // public com.reflect.exer.Animal(java.lang.String)

        System.out.println("*******华丽的分割线*******");

        // 2 getDeclaredConstructors() 获取当前运行时类的所有构造器
        Constructor<?>[] dConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> c : dConstructors) {
            System.out.println(c);
        }

        // public com.reflect.exer.Animal()
        // com.reflect.exer.Animal(java.lang.String,int)
        // public com.reflect.exer.Animal(java.lang.String)

        System.out.println("*******华丽的分割线*******");

        // 3 获取运行时的父类
        Class<?> superClass = clazz.getSuperclass();
        System.out.println(superClass); // class com.reflect.exer.Creature

        // 4 获取运行时的父类（带泛型） 注意这里是返回的是一个接口
        Type superGenericClass = clazz.getGenericSuperclass();
        System.out.println(superGenericClass); // com.reflect.exer.Creature<java.lang.String>

        System.out.println("*******华丽的分割线*******");

        // 5 获取运行时的父类（带泛型） 的 父类
        Type superGenericClass2 = clazz.getGenericSuperclass();
        ParameterizedType paraType = (ParameterizedType) superGenericClass2;
        // 获取泛型类型
        Type[] actualTypeArguments = paraType.getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            System.out.println(type.getTypeName()); // java.lang.String
            // 这里第二种获取方法就是使用getName 需要强制转换成 Class类型 虽然本身其实也就是一个Class类型
            System.out.println(((Class<?>) type).getName()); // java.lang.String
        }

        // 6 获取运行时类实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
            // interface java.lang.Comparable
            // interface com.reflect.exer.Myinterface
        }
        // 获取运行时父类的接口
        Class<?>[] pInterfaces = clazz.getSuperclass().getInterfaces();
        for (Class<?> pi : pInterfaces) {
            System.out.println(pi); // interface java.io.Serializable
        }

        // 7 获取运行类时候的包
        Package pack = clazz.getPackage();
        System.out.println(pack); // package com.reflect.exer
    }
}

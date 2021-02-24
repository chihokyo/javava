package com.reflect.exer;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * 通过反射创建对应的运行对象 反射动态创建对象
 * 
 * newInstance():调用此方法，创建对应的运行时类的对象。内部调用了运行时类的空参的构造器。

        要想此方法正常的创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够。通常，设置为public。


        在javabean中要求提供一个public的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
 */
public class InstanceByReflection {
    public static void main(String[] args) {

        // 1.这个就是泛型的类型决定了对象返回值，也就是对象是什么类型的
        // 这样就不用进行强行转换了
        Class<Person> clazz = Person.class;
        // Person obj = clazz.newInstance(); // 9之后过时了
        try {
            Person obj = clazz.getDeclaredConstructor().newInstance();
            // 使用泛型 不用强转 Person obj = (Person) clazz.getDeclaredConstructor().newInstance();
            System.out.println(obj);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        // 2.测试
        InstanceByReflection i = new InstanceByReflection();
        i.test();

    }

    /**
     * 测试动态创建对象
     */
    public void test() {
        // 这里使用循环进行随机生成对象
        for (int i = 0; i < 10; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.reflect.exer.Person";
                    break;
            }

            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个指定类的对象
     * @param classPath 指定类的全类名
     * @return
     */
    public Object getInstance(String classPath) throws Exception {
        Class<?> clazz = Class.forName(classPath);
        return clazz.getDeclaredConstructor().newInstance();
    }
}

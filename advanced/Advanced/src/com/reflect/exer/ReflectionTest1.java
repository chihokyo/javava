package com.reflect.exer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 关于反射的基本说明1
 * 
 * 如果2种方式都可以新建对象那么用哪一种
 * 优先使用new ，而对于无法预测的动态性的对象。选择使用反射。
 * 因为反射可以根据使用者的数据来确定生成哪个对象
 */
public class ReflectionTest1 {
    public static void main(String[] args) {

        System.out.println("********使用反射之前的Person实例操作********");
        // ********使用反射之前的Person实例操作********
        // 1.新建实例对象
        Person p1 = new Person("Tom", 12);
        // 2.通过对象调用内部的方法和属性
        p1.age = 10;
        System.out.println(p1.toString());
        p1.show();
        // 在Person类外部，不可以调用内部私有结构
        // p1.name = "Amy";
        // p1.showNation();

        System.out.println("********使用反射之后的Person实例操作********");
         // ********使用反射之后的Person实例操作********
         try {
            Class clazz = Person.class;
            // 1.通过反射创建一个Person类对象
            Constructor cons = clazz.getConstructor(String.class, int.class);
            Object obj = cons.newInstance("Jerry", 99);
            // 事实上这个obj就是一个Person
            Person p = (Person) obj;
            System.out.println(obj.toString()); // Person{name='Jerry', age=99}

            // 2. 调用对象指定的属性
            Field age = clazz.getDeclaredField("age");
            age.set(p, 100);
            System.out.println(p.toString()); // Person{name='Jerry', age=100}

            // 3.调用方法
            Method show = clazz.getDeclaredMethod("show");
            // 调用函数的时候没有参数就可以不用
            // show.invoke(obj, args) 有参数就用参数
            show.invoke(p);

            System.out.println("********通过反射调用私有属性or方法or构造器********");

            // 1.私有构造器
            Constructor cons1 = clazz.getDeclaredConstructor(String.class);
            cons1.setAccessible(true);
            Person p2 = (Person) cons1.newInstance("Jessica");
            System.out.println(p2); // Person{name='Jessica', age=0}
            // 2.私有属性
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            name.set(p2, "leee");
            System.out.println(p2); // Person{name='leee', age=0}
            // 3.私有方法
            Method showNation = clazz.getDeclaredMethod("showNation", String.class);
            showNation.setAccessible(true);
            // 相当于 String name = p2.showNation("China")
            String nation = (String) showNation.invoke(p2, "China");
            System.out.println(nation);

         } catch (Exception e) {
             e.printStackTrace();
         }

         


    }
}


/**
 * 新建一个测试Person类
 */
class Person {

    private String name;
    public int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person() {
        System.out.println("Person()");
    }

    public void show(){
        System.out.println("你好，我是一个人");
    }

    private String showNation(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }
}
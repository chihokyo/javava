package com.reflect.exer;

import java.io.Serializable;
import java.lang.reflect.*;

/**
 * 通过反射获取类的完整结构
 */
public class ReflectionTest5 {
    public static void main(String[] args) {

        Class<Animal> clazzA = Animal.class;

        // 1. 获取属性结构
        // getFields() 获取当前运行类&父类中声明为public访问权限的属性
        Field[] fields = clazzA.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // 这里的属性是一个数组。可以看到默认获得是id和weight
        // 因为只有这俩明确写了是 public
        // public int com.reflect.exer.Animal.id
        // public double com.reflect.exer.Creature.weight
        System.out.println("*******华丽的分割线");

        // 2.getDeclaredFields() 获取当前运行类的所有属性（不包含父类）
        Field[] field2 = clazzA.getDeclaredFields();
        for (Field field : field2) {
            System.out.println(field);
        }

        // 父类的就没了 比如 weight gender
        // private java.lang.String com.reflect.exer.Animal.name
        // int com.reflect.exer.Animal.age
        // public int com.reflect.exer.Animal.id
        // private static final long com.reflect.exer.Animal.serialVersionUID
        System.out.println("*******华丽的分割线");

        // 2. 获取权限修饰符 数据类型 变量名
        Class<Animal> clazzB = Animal.class;
        Field[] f = clazzB.getDeclaredFields();
        for (Field field : f) {
            // 1 权限修饰符
            // 这里的权限修饰符，是返回一个数字，这个数字代表的就是权限
            // 需要去转换
            int modifier = field.getModifiers();
            System.out.println(Modifier.toString(modifier)); // private static final
            // 2 数据类型
            Class<?> type = field.getType();
            System.out.println(type.getName()); // long
            // 3 变量名
            String fname = field.getName();
            System.out.println(fname); // serialVersionUID
            System.out.println("*******华丽的分割线");
        }

    }
}

class Creature<T> implements Serializable {

    private static final long serialVersionUID = 2L;
    private char gender;
    public double weight;

    private void breath() {
        System.out.println("生物都可以呼吸");
    }

    public void eat() {
        System.out.println("生物都要吃东西");
    }
}

/**
 * 自定义一个接口
 */
interface Myinterface {
    void info();
}

class Animal extends Creature<String> implements Comparable<String>, Myinterface {

    private String name;
    int age;
    public int id;

    private static final long serialVersionUID = 1L;

    public Animal() {

    }

    public Animal(String name) {
        this.name = name;
    }

    // 默认权限构造器
    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 私有方法 private
    private String show(String type) {
        System.out.println("Animal show()");
        return type;

    }

    // 共有方法 public
    public String descInfo(String desc) throws NullPointerException, ClassCastException {
        return desc;
    }

    public static void staticMethod() {
        System.out.println("这是Animal的静态方法staticMethod()");
    }

    // 重写接口方法 Myinterface
    @Override
    public void info() {
        System.out.println("Animal info()");
    }

    // 重写接口方法 Comparable
    @Override
    public int compareTo(String o) {
        return 0;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }

}
package com.generic.exer;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 泛型在继承方面的体现
 * 
 * G<A> 和 G<B> 不具备父子关系，而是并列关系 
 * 类A是类B的父类，那么A<G>和B<G>的父类
 * 
 */
public class GenericExtendTest {
    public static void main(String[] args) {
        // 1 普通多态的体现
        Object obj = null;
        String str = null;

        obj = str; // 因为str是obj的子类 → 多态特点

        // 2 数组方面
        Object[] objArr = null;
        String[] strArr = null;
        objArr = strArr; // ↑ 同理

        // 3 List泛型
        List<Object> list1 = null;
        List<String> list2 = null;
        // list1 = list2; NG 编译出错 因为类型不一样
        // 此时 list1 list2 不具有子父类关系 是并列关系

        // 反证法
        // 假设 list1 = list2;成立的话。
        // 那么list1 指向了 List<Object> 一个对象。
        // list2 也指向了 List<Object> 但是如果这个时候
        // list1使用add这种方法，修改成了 Integer 这个时候 list2只能放String的，泛型就没了意义

        // 5 虽然不同泛型的不构成子父类，但是同一泛型的子父类是可以的
        AbstractList<String> listA = null;
        List<String> listB = null;
        ArrayList<String> listC = null;

        listA = listC;
        listB = listC;
        // 这些都不会编译出错
        // 也就是说

    }

    // 4 不同泛型的不是重载，需要写俩
    public static void show1(List<String> list) {

    }

    public static void show2(List<Object> list) {

    }

}

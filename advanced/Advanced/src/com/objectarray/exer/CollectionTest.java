package com.objectarray.exer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
/**
 * 一、集合框架的概述
 *
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 *  说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi，数据库中）
 *
 * 2.1 数组在存储多个数据方面的特点：
 *      > 一旦初始化以后，其长度就确定了。
 *      > 数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了。
 *       比如：String[] arr;int[] arr1;Object[] arr2;
 * 2.2 数组在存储多个数据方面的缺点：
 *      > 一旦初始化以后，其长度就不可修改。
 *      > 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。
 *      > 获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用
 *      > 数组存储数据的特点：有序、可重复。对于无序、不可重复的需求，不能满足。
 *
 * 二、集合框架
 *      |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----List接口：存储有序的、可重复的数据。  -->“动态”数组
 *              |----ArrayList、LinkedList、Vector
 *
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet、LinkedHashSet、TreeSet
 *
 *      |----Map接口：双列集合，用来存储一对(key - value)一对的数据   -->高中函数：y = f(x)
 *              |----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 * 三 常用方法
 * add() addAll() size() isEmpty() clear()
 * 
 */
public class CollectionTest {
    public static void main(String[] args) {
        // 1 add​(E e) 数据e添加到集合coll中
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date()); // 自动装箱 其实放对象或者任何类型都可以

        // 2 size() 获取添加的元素的个数
        System.out.println(coll.size());

        // 3 addAll​(Collection<? extends E> c) 添加一个集合
        Collection coll2 = new ArrayList();
        coll2.add("CC");
        coll2.add(12);
        coll.addAll(coll2);
        System.out.println(coll.size()); // 6
        System.out.println(coll); // [AA, BB, 123, Thu Nov 05 14:30:19 JST 2020, CC, 12]

        // 4 isEmpty()判断是否为空
        System.out.println(coll.isEmpty()); // false

        // 5 清空集合元素
        coll.clear(); // 并非清空coll这个对象，而只是把里面的元素给清除掉。对象还是存在，所以不报空指针的错误
        System.out.println(coll.isEmpty()); // true

    }
}

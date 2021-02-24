package com.generic.exer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 关于通配符的使用
 * 1 为什么要使用通配符？↓
 * 
 * 类A是类B的父类 G<A>G<B>是没有关系的。二者共同的父类是G<?>
 * 
 * 2 get获取的类型是什么
 * 3 list可以添加吗
 */
public class WildCardGeneric {
    public static void main(String[] args) {

        // 1 为什么？面对这样写的代码，会多写很多。但是又没有重载
        List<Object> list1 = null;
        List<String> list2 = null;

        // ？作为通配符可以符合上面的情形
        List<?> list = null;
        list = list1;
        list = list2;

        WildCardGeneric.print(list1);
        WildCardGeneric.print(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        // 3 list可以添加吗？
        // 使用通配符的结构的时候，添加List<?>就不能添加数据了
        // 除了null
        // list.add()
        list.add(null);

        // 4 get可以获取的类型是什么 允许读取
        Object o = list.get(0); // 可以赋值给的共通父类就是Object
        System.out.println(o);
        
    }

    public static void print(List<?> list) {
        Iterator<?> ite = list.iterator();
        while (ite.hasNext()) {
            // 2 注意这里的结构，由于是？通配符。所以要适用于所有的情况。那就是Object
            Object obj = ite.next();
            System.out.println(obj);
        }
    }
}

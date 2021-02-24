package com.map.exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collections工具类
 * 
 * 首先 Collection PK Collections
 *  第一个是 数据类型的单列数据的接口 旗下有set和list神兵
 *  第二个是 Collections工具类 面向Map Set List 都有用
 * 
 * reverse(List)：反转 List 中元素的顺序
 * shuffle(List)：对 List 集合元素进行随机排序
 * sort(List)：根据元素的自然顺序对指定 List 集合元素按升序排序
 * sort(List，Comparator)：根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
 * swap(List，int， int)：将指定 list 集合中的 i 处元素和 j 处元素进行交换

 * Object max(Collection)：根据元素的自然顺序，返回给定集合中的最大元素
 * Object max(Collection，Comparator)：根据 Comparator 指定的顺序，返回给定集合中的最大元素
 * Object min(Collection)
 * Object min(Collection，Comparator)
 * int frequency(Collection，Object)：返回指定集合中指定元素的出现次数
 * void copy(List dest,List src)：将src中的内容复制到dest中
 * boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替换 List 对象的所有旧值
 */
public class CollectionsMethodTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(123);
        list.add(456);
        list.add(-56);
        list.add(0);
        list.add(1000);

        System.out.println(list); // [123, 456, -56, 0, 1000]
        Collections.shuffle(list); // [123, 0, 456, 1000, -56]
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list); // [-56, 0, 123, 456, 1000]

        Collections.swap(list, 2, 4);
        System.out.println(list); // [-56, 0, 1000, 456, 123]

        list.add(0);
        list.add(0);
        int fre = Collections.frequency(list, 0);
        System.out.println(fre); // 3

        // 异常 java.lang.IndexOutOfBoundsException: Source does not fit in dest
        // 看源码发现会先验证 dest 长度 长度小于就会报错
        // List<Integer> dest = new ArrayList<Integer>(); NG
        List<Object> dest = Arrays.asList(new Object[list.size()]); // 新建了一个都是null的list
        Collections.copy(dest, list);
        System.out.println(dest); // [-56, 0, 1000, 456, 123, 0, 0]

        System.out.println("*************华丽的分割线**************");
        // 工具类里有方法可以把 多个线程不安全的数据类型 转换成 线程安全的数据类型

        // 这个list1就是线程安全的
        List<Integer> list1 = Collections.synchronizedList(list);
        System.out.println(list1);
    }
}

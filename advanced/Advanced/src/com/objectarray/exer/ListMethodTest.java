package com.objectarray.exer;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * List接口实现类的常用方法
 * 因为List是有序的，所以会多了一个索引的操作。
 * 
 * void add(int index, Object ele):在index位置插入ele元素
 * boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
 * Object get(int index):获取指定index位置的元素
 * int indexOf(Object obj):返回obj在集合中首次出现的位置
 * int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
 * Object remove(int index):移除指定index位置的元素，并返回此元素
 * Object set(int index, Object ele):设置指定index位置的元素为ele
 * List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合

 * 总结：常用方法
 * 增：add(Object obj)
 * 删：remove(int index) / remove(Object obj)
 * 改：set(int index, Object ele)
 * 查：get(int index)
 * 插：add(int index, Object ele)
 * 长度：size()
 * 遍历：① Iterator迭代器方式
 *      ② 增强for循环
 *      ③ 普通的循环
 * 
 */
public class ListMethodTest {
   public static void main(String[] args) {

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new String("Amy"));
        list.add(false);
        list.add(new Person("Tom", 30));
        list.add(456);
        System.out.println(list);

        // 1 add(int index, Object element) 在指定位置增加元素 区别于 Collection 其他向后移动
        list.add(1, "CC");
        System.out.println(list);

        // 2 addAll(Collection c) 
        // 注意，如果这个时候不小心把 addAll 写成了 add 是不会报错的，但是就相当于添加整体了
        List listAll = Arrays.asList(1, 2, 3);
        
        // list.add(listAll);
        // System.out.println(list); // [123, CC, 456, Amy, false, com.objectarray.exer.Person@27e0ed, 456, [1, 2, 3]]
        // System.out.println(list.size()); // 8

        list.addAll(listAll);
        System.out.println(list); // [123, CC, 456, Amy, false, com.objectarray.exer.Person@27e0ed, 456, 1, 2, 3]
        System.out.println(list.size()); // 10

        // 3 get(int index) 获取指定位置的元素
        System.out.println(list.get(6)); //456

        // 4 indexOf(Object o) 返回obj首次出现的位置
        int result = list.indexOf(456);
        System.out.println(result); // 2

        int lresult = list.lastIndexOf(777);
        System.out.println(lresult); // 

        // 5 boolean remove(Object o) {
        // 这个方法参数是 Object 其实就是 Collectio 的 方法 
        // 写成index 其实就是重写了remove方法
        // // 这里是 Collectio
        // boolean obj = list.remove(false);
        // System.out.println(obj); // true 移除成功
        // System.out.println(list); // [123, CC, 456, Amy, com.objectarray.exer.Person@27e0ed, 456, 1, 2, 3]
        
        // 这里是重写过的，返回的是删除的元素
        // public E remove(int index) {
        Object obj = list.remove(5); //　com.objectarray.exer.Person@27e0ed
        System.out.println(obj);
        System.out.println(list);

        // 6 set 设置指定位置元素 没有返回值，直接修改
        list.set(0, "SET");
        System.out.println(list); // [SET, CC, 456, Amy, false, 456, 1, 2, 3]

        // 7 subList 获取一个子list 左闭右开
        // List<E> subList(int fromIndex, int toIndex) {
        List list2 = list.subList(2, 6);
        System.out.println(list2); // [456, Amy, false, 456]

        System.out.println("*********华丽的分割线*************：");
        
        // 关于迭代

        // 方式1 迭代器
        Iterator ite = list.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }

        // 方式2 for循环
        for (Object objList : list) {
            System.out.println(objList);
        }

        // 方式3 普通for
        for (int i = 0; i < list.size(); i++) {
            // 注意这里已经不是数组了，而是List数据 所以不能使用下标
            System.out.println(list.get(i));
        }

        System.out.println("********关于remove一个练习***********");

        List listRemove = new ArrayList();
        listRemove.add(1);
        listRemove.add(2);
        listRemove.add(3);

        // 1 默认remove就是删除索引2
        // listRemove.remove(2);
        System.out.println(listRemove); // [1, 2]
        // 2 如果想要删除数据2
        listRemove.remove(Integer.valueOf(2));
        System.out.println(listRemove); // [1, 3]

   } 
}

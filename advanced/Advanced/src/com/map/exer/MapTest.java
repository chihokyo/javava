package com.map.exer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 一、Map的实现类的结构：
 *  |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 *         |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 *              |----LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *                      原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 *                      对于频繁的遍历操作，此类执行效率高于HashMap。
 *         |----TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序
 *                      底层使用红黑树
 *         |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 *              |----Properties:常用来处理配置文件。key和value都是String类型
 *
 *
 *      HashMap的底层：数组+链表  （jdk7及之前）
 *                    数组+链表+红黑树 （jdk 8）
 *  【关于上面的总结】
 *      Map就是键值对
 *      旗下有3个孩子 1. HashMap 最常用的键值对 可以存储null 线程不安全所以效率高
 *                  LinkedHashMap 在上面原有的基础上增加了一堆指针
 *              2. TreeMap 键值对的树
 *              3. Hashtable 最古老的孩子 不能存储null 线程安全
 * 
 *  *  面试题：
 *  1. HashMap的底层实现原理？ 78不一样。
 *  2. HashMap 和 Hashtable的异同？ 上面写了
 *  3. CurrentHashMap 与 Hashtable的异同？（暂时不讲）
 *
 *  二、Map结构的理解：
 *    Map中的key:无序的、不可重复的，使用Set存储所有的key  
 *          ---> key 所在的类要重写 equals()和 hashCode() （以HashMap为例）
 *    Map中的value:无序的、可重复的，使用 Collection 存储所有的value 
 *          ---> value 所在的类要重写 equals() 为什么不用重写hashCode 因为判断的时候可以通过key直接找到，也没必要统一成一样的value
 *    一个键值对：key-value构成了一个Entry对象。
 *    Map中的 entry:无序的、不可重复的，使用Set存储所有的entry （Key都不重复了，entry整体肯定不可能重复啊）
 *  
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put(null, 124);
    }
}

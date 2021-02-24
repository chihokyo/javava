package com.objectarray.exer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 关于Set接口类的说明
 * Set主要有3种实现类的方式
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *                  |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
 *                                      对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
 *              |----TreeSet：可以按照添加对象的指定属性，进行排序。
 *【我来总结一下特点吧。HashSet其实就是一个数组，底层用的就是数组 + 链表】
    为什么又会有链表呢。是因为当code值一样的时候就会去比较是否相等，这个时候同一个数组index序列对应的是值 是一段链表穿起来的。
 *
 *  1. Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法。
 *
 *  2. 要求：向Set(主要指：HashSet、LinkedHashSet)中添加的数据，其所在的类一定要重写 hashCode() 和 equals()
 *     要求：重写的hashCode()和equals()尽可能保持一致性：相等的对象必须具有相等的散列码
 *      重写两个方法的小技巧：对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。
 *  3.关于无序性的说明。无序性并不是说随机性的问题。
 *   无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。
 * 
 *  4. 不可重复性：保证添加的元素按照equals()判断时，不能返回true.即：相同的元素只能添加一个。
 * 
 *  5. 我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
        此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
        数组此位置上是否已经有元素：
            如果此位置上没有其他元素，则元素a添加成功。 --->情况1
            如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
                如果hash值不相同，则元素a添加成功。--->情况2
                如果hash值相同，进而需要调用元素a所在类的equals()方法：
                       equals()返回true,元素a添加失败
                       equals()返回false,则元素a添加成功。--->情况2
        【1 计算 hashcode的值 找到index  2 看hash值是否相等 3 看equals结果】
        对于添加成功的情况2和情况3而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。
        jdk 7 :元素a放到数组中，指向原来的元素。
        jdk 8 :原来的元素在数组中，指向元素a
        总结：七上八下

        HashSet底层：数组+链表的结构。
 * 
 * 
 */
public class SetTest {
    public static void main(String[] args) {

        // Set set = new HashSet();
        // set.add(456);
        // set.add(123);
        // set.add(123);
        // set.add("AA");
        // set.add("CC");
        // set.add(new User("Tom",12));
        // set.add(new User("Tom",12));
        // set.add(129);

        // Iterator iterator = set.iterator();
        // while(iterator.hasNext()){
        //     System.out.println(iterator.next());
        // }

        Set set = new LinkedHashSet();

        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        
    }
}

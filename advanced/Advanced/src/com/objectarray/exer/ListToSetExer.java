package com.objectarray.exer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sun.tools.jstat.SymbolResolutionClosure;

/**
 * 练习1 小练习题 在List去除重复的数字值 最简单的方法是
 * 练习2 关于HashSet删除赋值的操作
 */
public class ListToSetExer {
    public static void main(String[] args) {

        /****************练习1**************** */
        List list = new ArrayList();
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        list.add(Integer.valueOf(3));
        list.add(Integer.valueOf(3));
        list.add(Integer.valueOf(4));
        List list2 = ListToSetExer.duplicateList(list);
        for (Object result : list2) {
            System.out.println(result);
        }
        
    }

    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(list);
    }

}


class List2 {

     /****************练习2 已经重写了hashcode 和 equals **************** */
    HashSet set = new HashSet();
    Person p1 = new Person(1001, "AA");
    Person p2 = new Person(1002, "BB");

    set.add(p1);
    set.add(p2);
    System.out.println(set); 
    // 肯定是AA BB 都包含
    
    p1.name = "CC";
    set.remove(p1);
    System.out.println(set);
    // BB CC
    // 修改了CC 因为remove之前要进行hashcode判断。
    // p1 p2 经过哈希值 位置是不一样的
    // p1.name 改成了 CC之后 
    // 这时候进行remove 1001 CC 就要先做判断哈希值 发现 哈希值
    // 因为当初p1的位置是按照AA生成的 所以CC的时候 哈希值肯定就不一样。于是就没有删除成功

    set.add(new Person(1001, "CC"));
    System.out.println(set);
    // 这个时候 BB CC CC
    // 因为这个时候添加之前又计算了一次哈希值 但是那个CC哈希值是没有任何东西的 所以又添加了一次

    set.add(new Person(1001, "AA"));
    System.out.println(set);
    // BB CC CC AA
    // 这个时候为什么又能添加成功呢。
    // 因为这个时候又计算了一次new Person(1001, "AA")位置，发现位置一样的地方已经换成了CC 所以是不一样的
    // 自然也会添加成功了

    // 1001 AA p1 地址1
    // 1002 BB p1 地址2
    // 这个时候修改了 AA为CC
    // 1001 CC p1 地址1
    // 1002 BB p1 地址2
    // 但是remove的时候 要计算是否相等 于是通过hashCode找到了地址1 但是在计算equals发现并不相等 所以没有删除成功
    // 这个时候问题1的结果就是 上面的

    // 第二步，这个时候要添加一个新的对象 1001 CC 
    // 由于地址1 还是以 1001 AA 计算出来的地址1
    // 但是1001 CC 计算的其实是地址3 于是添加成功如下
    // 1001 CC  地址1
    // 1002 BB  地址2
    // 1001 CC  地址3

    // 第三步 这个时候要添加 1001 AA 这个时候虽然从hashCode里找到了地址1
    // 但是在equals对比的时候发现这俩根本不一样 因为那个地方已经成了 CC
    // 于是 1001 AA 也添加成功

    // 1001 CC  地址1
    // 1002 BB  地址2
    // 1001 CC  地址3
    // 1001 AA  地址4
    
    // 所以说要重写那两个函数重新进行计算 尤其在HashSet这种结构中 计算是否相等
    // 第一步是根据对象先计算相应的哈希值 然后在进行equals进行对比
    
}
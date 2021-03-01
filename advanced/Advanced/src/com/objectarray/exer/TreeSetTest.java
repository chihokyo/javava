package com.objectarray.exer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * 红黑树实现
 * 1 向 TreeSet 添加数据 要求是相同类的对象
 * 2 自然排序 (Comparable接口) 和 定制排序（Comparator）
 * 3 自然排序当中 比较2个对象的标准是 compareTo()返回是0 而不是 equals（）
 * 4 定制排序当中 比较2个对象的标准是 compare()返回是0 而不是 equals（）
 */
public class TreeSetTest {
    public static void main(String[] args) {

        TreeSet set = new TreeSet();

        // NG 不能添加不同类的对象
        // set.add(124);
        // set.add("124");

        set.add(123);
        set.add(456);
        set.add(789);

        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }

        // 添加相同对象
        TreeSet set2 = new TreeSet();
        set2.add(new User("Tom", 18));
        set2.add(new User("Jim", 16));
        set2.add(new User("yes", 14));
        set2.add(new User("Tom", 12));

        Iterator ite2 = set2.iterator();
        while (ite2.hasNext()) {
            System.out.println(ite2.next());
        }

        System.out.println("*************华丽的分割线************");

        // 按照定制方式排序
        Comparator com = new Comparator() {

            // 按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                } else {
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        TreeSet set3 = new TreeSet(com);
        set3.add(new User("Tom", 18));
        set3.add(new User("Jim", 16));
        set3.add(new User("yes", 14));
        set3.add(new User("Tom", 12));

        Iterator ite3 = set3.iterator();
        while (ite3.hasNext()) {
            System.out.println(ite3.next());
        }
    }
}

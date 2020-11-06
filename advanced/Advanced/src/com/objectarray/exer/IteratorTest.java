package com.objectarray.exer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器Iterator作为借口
 * 1 内部的方法 hasNext() 和 next()
 * 2 集合对象每次调用 iterator（）都会得到一个新的对象，默认指针都在集合的第一个元素之前
 * 3 内部定义了remove 就可以在遍历的时候删除集合中的元素，这个方法不是集合对象remove() 而是迭代器对象的remove方法
 *      如果还没有调用next()或者上一次next之后已经调用了remove，那么就会报错
 * 4 这个迭代器主要用来迭代 Collection 不适用于 Map
 */
public class IteratorTest {
    public static void main(String[] args) {

        Collection coll = new ArrayList();
        coll.add(23);
        coll.add(false);
        coll.add(new String("hello"));
        coll.add(new Person("yes", 20));

        Iterator ite = coll.iterator();

        // 方法一
        // System.out.println(ite.next());
        // System.out.println(ite.next());
        // System.out.println(ite.next());
        // System.out.println(ite.next());
        // System.out.println(ite.next()); // 超过 java.util.NoSuchElementException

        // 方法二
        // 注意点1 必须是迭代器才能有next 集合计算长度用size 不能用length
        // for (int i = 0; i < coll.size(); i++) {
        //      System.out.println(ite.next());
        // }

        // 方法三 【推荐使用】
        // 通过对 hasNext()方法判断是否还有下一个元素
        while (ite.hasNext()) {
            // next() 2个用法 ① 指针下移 ② 将下移以后集合位置上的元素返回
            // 在 hasNext的时候指针没有下移 next下移了
            // ite 只用于迭代并非真正的容器 记住只是迭代，里面是不装任何数据的
            System.out.println(ite.next());
        }

        System.out.println("**********华丽的分割线***********");

        // // 【错误方法大全】1
        // // 这个错误就是 在判断 ite.next() 已经进行的返回了 这样不仅会错过一次，还会报错
        // while (ite.next() != null) {
        //     System.out.println(ite.next());
        // }

        // // 【错误方法大全】2
        // // 原因就是 每一次iterator()的调用都会得到一个全新的迭代器对象，默认右边都在集合的第一个要素之前
        // 新的迭代器对象的指针是从开头开始的，所以会始终都是第一个元素
        // while (coll.iterator().hasNext()) {
        //     System.out.println(ite.next());
        // }
        
        // 3 关于 remove()

        Collection coll2 = new ArrayList();
        coll2.add(23);
        coll2.add(false);
        coll2.add(new String("hello"));
        coll2.add(new Person("yes", 20));

        // 1 生成一个迭代器
        Iterator ite2 = coll2.iterator();

        while (ite2.hasNext()){
            // 2 这个时候要注意，如果要删除必须在next之后调用
            // 不然这个时候是没有值的
            // 比如这样直接删除 iterator.remove() NG
            // 指针还没有下来这个时候直接调用会报错  java.lang.IllegalStateException
            // ite2.remove();
            Object obj2 = ite2.next();
            if ("hello".equals(obj2)) {
                ite2.remove();
            }
        }

        // 这个时候如果进行遍历的话，要重新生成迭代器的
        ite2 = coll2.iterator();
        while (ite2.hasNext()) {
            System.out.println("ite2-" + ite2.next());
        }

    }
}

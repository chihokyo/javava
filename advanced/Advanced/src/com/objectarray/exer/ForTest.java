package com.objectarray.exer;

import java.util.ArrayList;
import java.util.Collection;
/**
 * JDK5之后新增的 for方法用来迭代
 * 
 */
public class ForTest {
    public static void main(String[] args) {

        Collection coll = new ArrayList();
        coll.add(23);
        coll.add(false);
        coll.add(new String("hello"));
        coll.add(new Person("yes", 20));
        /**
         * for 遍历集合
         * for(集合中元素类型 局部变量:集合对象)
         * 类型由 集合对象元素属性决定
         * 变量就是一个局部变量，类似于以前的循环中的i
         * 如果是遍历数组
         */
        for (Object obj : coll) {
            // 整体过程就是把 coll 一次一次的赋值给 obj 然后输出
            // 所以类型就是coll的类型
            System.out.println(obj);
        }

        String[] arrTest = new String[]{"123","456","hello"};
        // for(数组元素类型 局部变量: 数组变量)
        for (String string : arrTest) {
            System.out.println(string);
        }

        // 测试题 比较2种方法遍历数组的差异

        String[] arrTest2 = new String[]{"123","456","hello"};

        // 普通循环数组 [YES,YES,YES]
        // for (int i = 0; i < arrTest2.length; i++) {
        //     arrTest2[i] = "YES";
        // }
        
        System.out.println("****华丽的分割线****");

        // 增强for循环数组 ["123","456","hello"]
        for (String string : arrTest2) {
            string = "NO";
        }

        // 输出结果看差异
        // 说明，普通循环是直接修改原来的数组 arr[i]
        // for循环数组使用的是 新建了一个局部变量 string 于是修改里面的变量的值
        // 跟原来的数组无关
        for (int i = 0; i < arrTest2.length; i++) {
            System.out.println(arrTest2[i]);
        }
        
    }
}
 
package com.jdk8.exer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda具体使用
 * 
 * 不同于 Python 
 *  Lambda 在java里不是函数，是一个对象。
 * 
 * () -> System.out.println("我是没有参数，没有返回值的Lambda");
 * 
 * ↑ 就是一个对象，是谁的对象呢 是左边接口的对象。 Runnable r2 =
 * 本质就是一个接口的实例对象 没有接口没有意义了
 * 
 * 总结 1 lambda 左边：形参列表
 *          参数类型可以省略（类型推断）
 *          如果只有一个参数，那么()也可以省略
 *     2 lambda 右边：lambda体
 *          使用一对{}进行包裹，如果只有一条执行语句，那么可以省略{}
 *          如果只有一条语句并且是return 那么return 也可以省略
 * 
 */
public class LambdaTest2 {
    public static void main(String[] args) {

        // 1 语法一：参数无，返回值无
        System.out.println("******");
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                System.out.println("我是没有参数，没有返回值的");
            }
        };
        r1.run();
        Runnable r2 = () -> {
            System.out.println("我是没有参数，没有返回值的Lambda");
        };
        r2.run();
        Runnable r3 = () -> System.out.println("我是没有参数，没有返回值的Lambda");
        r3.run();

        // 2 语法二：参数：1，返回值无
        System.out.println("******");
        Consumer<String> con1 = new Consumer<String>(){
            public void accept(String t) {
                System.out.println("我是谁:" + t); // 我是谁:yes
            };
        };
        con1.accept("yes");
        Consumer<String> con2 = (String s) -> System.out.println("她是谁:" + s);
        con2.accept("no"); // 她是谁:no

        // 3 使用类型推断，可以不写参数的数据类型
        // 以前的类型推断有这些。
        // ArrayList<String> list = new ArrayList<>();
        // int[] arr = {1, 2, 3}; 肯定是一个int数组，也就不用 int[] arr = new int[]{1, 2, 3}
        Consumer<String> con3 = (s) -> System.out.println("谁都不是:" + s);
        con3.accept("对的"); // 谁都不是:对的
        
       
        // 4 Lambda 如果只需要一个参数的时候，参数的括号可以省略
        Consumer<String> con4 = s -> System.out.println("不对吧:" + s);
        con4.accept("对的"); // 不对吧:对的

        // 5 多条语句，并且有返回值
        Comparator<Integer> com = new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1); // 19
                System.out.println(o2); // 100
                return o1.compareTo(o2);
            };
        };
        System.out.println(com.compare(19, 100)); // -1
        System.out.println("****");
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1); // 100
            System.out.println(o2); // 88
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(100, 88)); // 1

        // 6 当lambda只有一条语句，且是return的时候。那么return和大括号都可以省略
        Comparator<Integer> com3 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com3.compare(99, 20)); // 1
    }
}

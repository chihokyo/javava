package com.jdk8.exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 关于【函数式接口】FunctionalInterface 
 * 1 一个接口只声明了1个抽象方法，那么这个接口就是函数式接口。jdk8开始的。
 * 
 * 2 @FunctionalInterface 注解 加和不加都可以，但是如果加上，当你写多了一个抽象方法的时候，就会进行提示。
 * 
 * 3 java内置的四大核心函数式接口
 *  消费性接口 Consumer<T> void accept(T t)
 *  供给型接口 Supplier<T> T get()
 *  函数型接口 Function<T, R> R apply(T t)
 *  断定型接口 Predicate<T> boolean test(T t)
 * 
 */
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        // 第一个案例，使用 消费性接口 accept
        // 直接调用
        happy(6000, new Consumer<Double>() {
            @Override
            public void accept(Double t) {
                System.out.println("最近买了一台手机价格是：" + t); // 最近买了一台手机价格是：6000.0
            }
        });

        System.out.println("**华丽的分割线***");
        happy(7000, money -> System.out.println("我最近买了一台手机价格是：" + money)); // 我最近买了一台手机价格是：7000.0



        System.out.println("**************************************************");



        // 第二个案例，使用断定型接口 进行过滤
        List<String> list = Arrays.asList("abc", "caa", "ddd", "bnc", "khn");

        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("abc");
            };
        });
        System.out.println(filterStrs);


        System.out.println("**华丽的分割线***");
        List<String> filterStrs2 = filterString(list, s -> s.contains("abc"));
        System.out.println(filterStrs2);

    }

    public static void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    // 写入一个数组，进行过滤
    public static List<String> filterString(List<String> list, Predicate<String> pre) {

        ArrayList<String> filterList = new ArrayList<>();
        // 这里循环的是list 不是 filterList 循环的是传入的数组
        for (String string : list) {
            if (pre.test(string)) {
                filterList.add(string);
            }
        }
        return filterList;

        
    }
}
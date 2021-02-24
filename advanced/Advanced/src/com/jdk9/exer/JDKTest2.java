package com.jdk9.exer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * JDK9新特性
 * 4 钻石操作符的升级
 * 5 try操作升级
 */
public class JDKTest2 {
    public static void main(String[] args) {
        // 4 钻石操作符的升级
        // 钻石操作符与匿名内部类在java8中不能共存，但是9可以
        // new Comparator<>() 就是这里的钻石符号
        Comparator<Object> com = new Comparator<>() {
            public int compare(Object o1, Object o2) {
                return 0;
            };
        };
        // jdk7新特性 类型推断 后面默认省略了 new ArrayList<String>();
        ArrayList<String> list = new ArrayList<>();
        System.out.println(list);

        // 关于try升级
        System.out.println("jdk8之前");
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            char[] cbuf = new char[20];
            int len;
            if ((len = reader.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // jdk8之后资源关闭操作的话，jdk8可以实现自动关闭
        // 要求自定关闭的资源必须放在try的小括号里
        System.out.println("jdk8");
        try (InputStreamReader reader2 = new InputStreamReader(System.in)) {
            char[] cbuf = new char[20];
            int len;
            if ((len = reader2.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // jdk9中的资源操作，小括号外也可以了。
        // 但是这个时候的资源是称量，声明为final，不能被随便修改
        System.out.println("jdk9");
        InputStreamReader reader3 = new InputStreamReader(System.in);
        try (reader3) {
            char[] cbuf = new char[20];
            int len;
            if ((len = reader3.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
            // 不可修改
            // reader3 = null NG
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

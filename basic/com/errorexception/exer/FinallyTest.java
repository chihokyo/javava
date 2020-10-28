package com.errorexception.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * try-catch-finally中finally的使用：
 * 
 * 
 * 1.finally是可选的
 * 
 * 2.finally中声明的是一定会被执行的代码。即使catch中又出现异常了，try中有return语句，catch中有
 * return语句等情况。
 * 
 * 3.像数据库连接、输入输出流、网络编程Socket等资源，JVM是不能自动的回收的，我们需要自己手动的进行资源的
 *   释放。此时的资源释放，就需要声明在finally中。
 * 
 */
public class FinallyTest {
    public static void main(String[] args) {
        FinalTestClass f = new FinalTestClass();
        // f.test1();
        f.test2();
    }
}


class FinalTestClass {

    public void test1() {
        try {
            int a = 10;
            int b = 0;
            System.out.println( a / b);
        } catch (ArithmeticException e) {
            // a = 200; NG
            e.printStackTrace();
            // 如果使用了 finally 即使下面这段代码也出现错误了。
            // 争取也会执行 finally 如果没有 finally 这样程序会直接退出
            int[] arr = new int[10];
            System.out.println(arr[10]);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 为什么要写呢
            // finally一定会被执行，即使有return
            System.out.println("test1 finally");
        }
    }

    public void testMethod() {
        int num = method();
        System.out.println(num);

    }

    public int method() {
        try {
            int[] arr = new int[10];
            System.out.println(arr[10]);
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 2;
        } finally {
            // 没有异常的情况下，finally 优先于 return执行
            System.out.println("finally");
            // return 3; // 如果return2 和 return3只
        }
    }


    public void test2() {
        // 这样还是报错
        FileInputStream fis = null;
        try {
            // 相对路径，当前工程目录下新建hello.txt
            File file = new File("hello.txt");
            fis = new FileInputStream(file); // 因为本身还有可能有异常，所以下面要判断是否null
            int data = fis.read();
            while (data != -1) {
                System.out.println((char)data);
                data = fis.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 放在这里，但是会显示未定义
            try {
                if (fis != null)
                    fis.close();      
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void test3() {
        
    }
}

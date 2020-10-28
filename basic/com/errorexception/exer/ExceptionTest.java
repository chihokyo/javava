package com.errorexception.exer;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Scanner;

/**
 * 一，异常体系结构
 * 
 * java.lang.Throwable
 * 		|-----java.lang.Error:一般不编写针对性的代码进行处理。
 * 		|-----java.lang.Exception:可以进行异常的处理
 * 			|------编译时异常(checked)
 * 					|-----IOException
 * 						|-----FileNotFoundException
 * 					|-----ClassNotFoundException
 * 			|------运行时异常(unchecked,RuntimeException)
 * 					|-----NullPointerException
 * 					|-----ArrayIndexOutOfBoundsException
 * 					|-----ClassCastException
 * 					|-----NumberFormatException
 * 					|-----InputMismatchException
 * 					|-----ArithmeticException
 */
public class ExceptionTest {
    public static void main(String[] args) {
       
        
    }
}


class ErrorFileTest {
    
    /**********编译异常***********/

    // public void test1() {
    //     File file = new File("hello.txt");
    //     FileInputStream fis = new FileInputStream(file);
        
    //     int data = fis.read();
    //     while (data != -1) {
    //         System.out.print((char)data);
    //         data = fis.read();
    //     }

    //     fis.close();
    // }
    

    /**********运行时异常***********/

    public void test() {
        int a = 10;
        int b = 0;
        System.out.println(a / b) ; // ArithmeticException
    }

    // InputMismatchException 只要输入的不是数字
    public void tes1() {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();

        System.out.println(score);
        scanner.close();
    }

    public void test2() {
        String str = "123";
        str = "abc";
        int num = Integer.parseInt(str); // NumberFormatException
    }

    public void test3() {
        // 强转。
        // String str = new Date();NG 类型都不一样，怎么赋值

        // ClassCastException
        Object obj = new Date();
        String str = (Srting)obj;
    }

    public void test4() {
        // ArrayIndexOutOfBoundsException
        // 下标超过了
        int[] arr = new int[10];
        System.out.println(arr[10]);

        // StringIndexOutOfBoundsException
        String str = "abc";
        System.out.println(str.charAt(3));
    }

    // NullPointerException
    // 空指针
    public void test5() {
        int[] arr = null;
        System.out.println(arr[8]);

        String str = "abc";
        str = null;
        System.out.println(arr[0]);

    }
    

}
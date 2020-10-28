package com.errorexception.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
 * 一、异常的处理：抓抛模型
 * 
 * 过程一："抛"：程序在正常执行的过程中，一旦出现异常，就会在异常代码处生成一个对应异常类的对象。
 *           并将此对象抛出。
 *           一旦抛出对象以后，其后的代码就不再执行。
 * 		
 * 		关于异常对象的产生：① 系统自动生成的异常对象
 * 					 ② 手动的生成一个异常对象，并抛出（throw）
 * 
 * 过程二："抓"：可以理解为异常的处理方式：① try-catch-finally  ② throws
 * 
 * 
 * 二、try-catch-finally的使用
 * 
 * try{
 * 		//可能出现异常的代码
 * 
 * }catch(异常类型1 变量名1){
 * 		//处理异常的方式1
 * }catch(异常类型2 变量名2){
 * 		//处理异常的方式2
 * }catch(异常类型3 变量名3){
 * 		//处理异常的方式3
 * }
 * ....
 * finally{
 * 		//一定会执行的代码
 * }
 * 
 * 说明：
 * 1. finally是可选的。
 * 2. 使用try将可能出现异常代码包装起来，在执行过程中，一旦出现异常，就会生成一个对应异常类的对象，根据此对象
 *    的类型，去catch中进行匹配
 * 3. 一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常的处理。一旦处理完成，就跳出当前的
 *    try-catch结构（在没有写finally的情况）。继续执行其后的代码
 * 4. catch中的异常类型如果没有子父类关系，则谁声明在上，谁声明在下无所谓。
 *    catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面。否则，报错
 * 5. 常用的异常对象处理的方式： ① String  getMessage()    ② printStackTrace()
 * 6. 在try结构中声明的变量，再出了try结构以后，就不能再被调用
 * 7. try-catch-finally结构可以嵌套
 * 
 * 体会1：使用try-catch-finally处理编译时异常，是得程序在编译时就不再报错，但是运行时仍可能报错。
 *     相当于我们使用try-catch-finally将一个编译时可能出现的异常，延迟到运行时出现。
 *     
 * 体会2：开发中，由于运行时异常比较常见，所以我们通常就不针对运行时异常编写try-catch-finally了。
 *      针对于编译时异常，我们说一定要考虑异常的处理。
 */
public class ExceptionTest2 {
    public static void main(String[] args) {
        Exception2 e = new Exception2();
        // e.test1();
        // e.test2();
        // e.test3();
        e.test4();

        // NumberFormatException!!
        // -----test2-----
        
    }
}


class Exception2 {

    public void test1() {
        String str = "123";
        str = "yy";

        try {
            int num = Integer.parseInt(str); // 到这里出现异常程序就停止了，所以下面的语句并不会输出
            System.out.println("-----test1-----");
            // NumberFormatException 这里不能写错
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException!!");
        } catch (Exception e) {
            // 一旦匹配就跳出，不会执行
            System.out.println("Exception!!");

        }
        System.out.println("-----test2-----");
    }

    public void test2() {
        String str1 = "123";
        str1 = "yy";
        // try {
            
        // } catch (Exception e) {
        //     //TODO: handle exception
        // } catch (NumberFormatException e) {
        //     因为Exception 是父类 NumberFormatException 是子类，
        //     包含关系的情况下，下面的NumberFormatException 永远不可能执行，编译就会出错。
        // }
    }

    public void test3() {
        String str2 = "123";
        str2 = "yy";

        try {
            int num = Integer.parseInt(str2); // 到这里出现异常程序就停止了，所以下面的语句并不会输出
            System.out.println("-----test1-----");
            // NumberFormatException 这里不能写错
        } catch (NumberFormatException e) {
            // 处理方式1
            // 输出 message getMessage()
            System.out.println(e.getMessage());
            System.out.println("NumberFormatException ");

            // 处理方式2 包含了程序所有的执行顺序
            // e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception!!");

        }
        System.out.println("-----test2-----");

        // try结构声明的变量不能被外面调用。和if很像
        // System.out.println(num);

    }

    public void test4() {
        try {
            File file = new File("hello.txt");
            FileInputStream fis = new FileInputStream(file);
                
            int data = fis.read();
            while (data != -1) {
                System.out.print((char)data);
                data = fis.read();
            }
        
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // 继续向下执行
            System.out.println("-----FileNotFoundException-----");
        } catch (IOException e) {
            // IOException 是 FileNotFoundException 的子类 所以位置不能颠倒
            System.out.println("-----IOException-----");
            e.printStackTrace();
        }
    }
}
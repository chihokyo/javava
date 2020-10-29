package com.errorexception.exer;
/**
 * 手动抛出异常的练习
 * 1 throws 把异常抛上去
 * 2 throw 主动抛出异常
 * 3 为什么要主动抛出异常，因为可以交给上面去处理
 */
public class StudentThrowTest {
    public static void main(String[] args) {
        try {
            Student s = new Student();
            s.regist(-100); // 下面跑上来的异常会到这里，所以用trycatch来处理
            System.out.println(s);
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());

            // 这里只是输出，并不是异常
            // Exception: 您输入的数据非法
        }
    }
}

class Student {

    private int id;

    public void regist(int id) throws Exception {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("这里只是输出，并不是异常");
            // throw new RuntimeException("RuntimeException: 您输入的数据非法"); //
            // 只有运行才能出现这里主动抛出一个异常
            // throw new Exception("Exception: 您输入的数据非法"); // 这里主动抛出一个异常 + throws Exception
            // ↑ 这里只是生成了一个异常对象，在方法内。

            // 关于自定义异常 这里抛出的异常必须是一个异常类的对象
            // throw new String("输入的非法");
            throw new MyException("输入的非法");
        }
    }
}

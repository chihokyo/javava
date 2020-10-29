package com.errorexception.exer;

public class ExceptionExer {
    public static void main(String[] args) {

        /**************************************/
        try {
            methodA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // methodA
        // methodA finally
        // 制造异常

        /**************************************/
        methodB();

        // methodB
        // methodB finally
    }

    public static void methodA() {
        try {
            System.out.println("methodA"); // ①
            throw new RuntimeException("制造异常");// ③这里要向上去抛。然后上面的Exception catch 到之后 System.out.println(e.getMessage());
        } finally {
            System.out.println("methodA finally");// ②
        }
    }
    public static void methodB() {
        try {
            System.out.println("methodB"); // ①
            return; // 没有出问题，但是 直接return出去
        } finally {
            System.out.println("methodB finally");// ②
        }

    }
}

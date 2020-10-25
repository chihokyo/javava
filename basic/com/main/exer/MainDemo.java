package com.main.exer;
/**
 * 控制台交互示例
 * 
 * 控制台 input com.main.exer.MainDemo 1 2 4
 * 控制台 output 
 * ***1
 * ***2
 * ***4
 * 
 * 控制台 input com.main.exer.MainDemo 8989
 * 控制台 output 
 * ***8989
 * ###8989
 * 
 */
public class MainDemo {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("***" + args[i]);

            int num = Integer.parseInt(args[i]);
            System.out.println("###" + num);

        }
    }
}

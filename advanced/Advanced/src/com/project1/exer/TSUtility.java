package com.project1.exer;

import java.io.*;
import java.util.*;
/**
 * 工具类，实现键盘访问
 * @version
 * @author chin
 */
public class TSUtility {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * 获取方法读取键盘，输入1-4任意字符。
     */
    public static char readMenuSelection() {
        char c;
        // for（；；）相当于while（1） 一直循环
        for (;;) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.println("选择错误，请重新输入");
            } else
                break;
        }
        return c;
    }

    /**
     * 该方法提示并等待，直到用户按回车键后返回
     */
    public static void readReturn() {
        System.out.println("按回车键继续...");
        readKeyBoard(100, true);
    }

    /**
     * 读取长度不超过2位的整数作为返回
     */
    public static int readInt() {
        int n;
        for (;;) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return n;
    }

    /**
     * 读取 Y or N 确认是否
     */
    public static char readConfirmSelection() {
        char c;
        for (;;) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.println("选择错误，请重新输入");
            }
        }
        return c;
    }

    /**
     * 读取键盘数字
     */
    public static String readKeyBoard(int limit, boolean blankReturn) {

        String line = "";
        while (scanner.hasNextLine()) {
            if (line.length() == 0) {
                if (blankReturn)
                    return line;
                else
                    continue;
            }
            if (line.length() < 1 || line.length() > limit) {
                System.out.println("输入长度有误，+ " + limit + " + 请重新输入:");
                continue;
            }
            break;
        }
        return line;
    }
}

package com.io.exer;

import java.io.*;

/**
 * 其他流的使用 
 * 1 标准的输出，输入流 
 *  1-1 标准输入流 默认从键盘输入 system.in 
 *  1-2 标准输出流 默认输出到控制台 system.out
 * 
 * 2 打印流 PrintStream 和PrintWriter
 *  2.1 提供了一系列重载的print() 和 println()
 * 
 * 
 * 3 数据流
 *  3.1 DataInputStream 和 DataOutputStream
 *  3.2 作用：用于读取或写出基本数据类型的变量或字符串
 */
public class OtherStreamTest {
    public static void main(String[] args) {

        // SystemInToInputStreamReader();

        ConsoleSetOutToFile();
    }

    /**
     * 练习1 System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流。
     * 实现一个demo，当键盘输入字符串。要求整行大写输出。
     * 如果输入的是e 或者是 exit程序就退出
     * 
     * 方法一：使用Scanner实现，调用next()返回一个字符串
     * 方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()
     */
    public static void SystemInToInputStreamReader() {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("请输出字符串，输入e或者exit退出");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束!");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println("成功输入: " + upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 练习2
     */
    public static void ConsoleSetOutToFile() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("countSrc.txt");
            // 创建打印输出流，设置为自动刷新模式，写入换行符或者 '\n' 都会刷新输出缓冲区
            ps = new PrintStream(fos, true);
            if (ps != null) {
                // 标准输出流（控制台输出）改成文件
                // 本来这里应该是输出到控制台的一段代码，
                // 但是通过这个打印流，将结果打印到了目标文件ps里
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) {
                System.out.print((char)i);
                if (i % 50 == 0) {
                    // 50倍数换行
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        
        // // 把从0到255的ascii数组转换成char
        // for (int i = 0; i <= 255; i++) {
        //     System.out.print((char)i);
        //     if (i % 50 == 0) {
        //         // 50倍数换行
        //         System.out.println();
        //     }
        // }
    }
}

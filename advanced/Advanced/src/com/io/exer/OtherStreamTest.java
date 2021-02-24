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
 * 3 数据流 
 *  3.1 DataInputStream 和 DataOutputStream 
 *  3.2 作用：用于读取或写出基本数据类型的变量或字符串
 */
public class OtherStreamTest {
    public static void main(String[] args) {

        // SystemInToInputStreamReader();

        // ConsoleSetOutToFile();

        OutputDataToFile();
        InputDataToFile();
    }

    /**
     * 练习1 System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流。
     * 实现一个demo，当键盘输入字符串。要求整行大写输出。 如果输入的是e 或者是 exit程序就退出
     * 
     * 方法一：使用Scanner实现，调用next()返回一个字符串 方法二：使用System.in实现。System.in ---> 转换流 --->
     * BufferedReader的readLine()
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
                System.out.print((char) i);
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
        // System.out.print((char)i);
        // if (i % 50 == 0) {
        // // 50倍数换行
        // System.out.println();
        // }
        // }
    }

    /**
     * 练习3 内存中的数据输出到文件里 用于读取或写出基本数据类型的变量或字符串 注意，data.txt 这个文件的目的并不是点击查看。
     * 而是通过读取来操作。所以下面有个读取方法
     */
    public static void OutputDataToFile() {
        DataOutputStream dos = null;
        try {
            // 1 新建一个数据流
            dos = new DataOutputStream(new FileOutputStream("data.txt"));
            // 2. 写数据
            dos.writeUTF("hello!我是谁");
            dos.flush();// 刷新操作，将内存中的数据写入文件
            dos.writeInt(23);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量里 注意，读取不同类型的数据的顺序要和写入文件保存的顺序一致
     */
    public static void InputDataToFile() {

        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new FileInputStream("data.txt"));
            // 这里的读取顺序要一致
            String name = dis.readUTF();
            int age = dis.readInt();
            boolean isMale = dis.readBoolean();

            System.out.println("name = " + name);
            System.out.println("age = " + age);
            System.out.println("isMale = " + isMale);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

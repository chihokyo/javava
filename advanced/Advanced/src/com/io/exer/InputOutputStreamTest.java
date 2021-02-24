package com.io.exer;

import java.io.*;

/**
 * 处理流之二转换流的使用
 * 
 * 1 转换流 属于字符流
 * 
 * 2 作用 提供字节流与字符流之间的转换
 * 
 * 3 解码 字节、字节数组 → 字符数组 字符串 编码 字符数组 字符串 → 字节、字节数组
 */
public class InputOutputStreamTest {
    public static void main(String[] args) {

        // test();

        test2();
    }

    /**
     * 字节输入流 → 字符的输入流转换
     */
    public static void test() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream("countSrc.txt");
            // 没有指定字符集的情况下，使用系统默认的字符集
            // InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            isr = new InputStreamReader(fis);
            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 综合使用转换流
     * utf8文件 转换成 gbk文件
     * 中间使用了字节流，然后转换成了字符流
     */
    public static void test2() {
        File fileUTF = new File("countSrc.txt");
        File fileGBK = new File("countSrc_GBK.txt");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        
        try {
            fis = new FileInputStream(fileUTF);
            fos = new FileOutputStream(fileGBK);

            isr = new InputStreamReader(fis, "utf-8");
            osw = new OutputStreamWriter(fos, "gbk");

            char[] cbuf = new char[20];
            int len;
            while ( (len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                 isr.close();   
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                 osw.close();   
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
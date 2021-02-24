package com.io.exer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 写出写入操作的练习 复制一个文件到另一个地方 相当于写入又写出了
 * 
 */
public class FileReaderWriterTest4 {
    public static void main(String[] args) {

        // 1. 初始化文件流对象
        FileReader fr = null;
        FileWriter fw = null;
        // 2.创建文件对象指名写入写出文件
        File srcFile = new File("toHi.txt");
        File destFile = new File("destFile.txt");

        // 【注意，图片不能使用字符流进行写入写出操作】
        // File srcFile = new File("toHi.jpg"); NG
        // File destFile = new File("destFile.jpg"); NG

        // 3.写出写入操作
        try {
            // 4. 文件流写入写出
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 设置
            char[] cbuf = new char[5];
            int len; //记录每次读入到cbuf数组中的字符的个数
            while ( (len = fr.read(cbuf)) != -1) {
                //每次写出len个字符
                fw.write(cbuf,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5.关闭文件流
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

    }
}

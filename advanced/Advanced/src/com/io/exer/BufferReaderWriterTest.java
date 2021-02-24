package com.io.exer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 使用 BufferedReader 和 BufferedWriter 实现文本文件的复制
 */
public class BufferReaderWriterTest {
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(new File("hi.txt")));
            bw = new BufferedWriter(new FileWriter(new File("hi2.txt")));

            // // 方法一
            // char[] cbuf = new char[1024];
            // int len;
            // while ((len = br.read(cbuf)) != -1) {
            //     bw.write(cbuf, 0, len);
            //     bw.flush();
            // }

            // 方法二
            String data;
            while ((data = br.readLine()) != null) {
                // 1
                // bw.write(data + "\n"); // data不包含换行符
                // 2
                bw.write(data);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

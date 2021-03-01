package com.io.exer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 缓冲流的使用
 * 1 缓冲流有哪些
 *      BufferedInputStream
 *      BufferedOutputStream
 *      BufferedReader
 *      BufferedWriter
 * 
 * 2 作用是什么
 *      提高流的读取写入的速度，提高速度的原因：提供了缓冲区
 * 
 * 3 处理流。就是在基础流的基础进行增强。
 */

public class BufferInputStreamTest {
    public static void main(String[] args) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            // 1 文件对象
            File srcFile = new File("srcPic.jpg");
            File destFile = new File("destPic.jpg");

            // 2 流
            // 2-1 节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            // 2-2 缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3 复制操作

            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                // bos.flush(); 刷新缓冲区。由于方法里有调用，这里可以不写
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4 关闭流，这里一定要注意，先关闭外层的流。在关闭内存的流
            // 在关闭外层的流同时，其实内层的流也会关闭。所以这里只需要关闭缓冲流就够了
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

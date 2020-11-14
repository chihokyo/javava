package com.io.exer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 节点流 PK 缓冲流
 * 
 * 其实就是普通的文件处理的流 PK 一下加了装备的缓冲流
 * FileReader PK BufferedInputStream
 * FileWriter PK BufferedOutputStream
 * 
 * 结果差距明显呢。
 * 缓冲流明显更快！！
 */

public class BufferInputStreamTest2 {
    public static void main(String[] args) {

        // 测试开启
        String srcPath = "/Users/Chihokyo/Code/javava/advanced/Advanced/srcPic.jpg";
        String destPath = "/Users/Chihokyo/Code/javava/advanced/Advanced/destPic.jpg";

        System.out.println("开始节点流的处理...");
        long startNoBufferFile = System.currentTimeMillis(); 
        noBufferFile(srcPath, destPath);
        long endNoBufferFile = System.currentTimeMillis();
        System.out.println("成功复制！节点流复制操作花费的时间为: " + (endNoBufferFile - startNoBufferFile));

        System.out.println("开始缓冲流的处理...");
        long startBufferFile = System.currentTimeMillis();
        bufferFile(srcPath, destPath);
        long endBufferFile = System.currentTimeMillis();
        System.out.println("成功复制！缓冲流复制操作花费的时间为: " + (endBufferFile - startBufferFile));
    }

    // 使用节点流实现指定路径下对文件的复制
    public static void noBufferFile(String srcPath, String destPath) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] data = new byte[10];
            int len;
            while ((len = fis.read(data)) != -1) {
                fos.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用缓冲流实现指定路径下对文件的复制
    public static void bufferFile(String srcPath, String destPath) {
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);

        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

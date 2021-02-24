package com.io.exer;

import java.io.*;

/**
 * 关于FileInputStream流的使用 
 * 1 文本文件 使用字符流 → 
 * 2 非文本文件 使用字节流 →
 */
public class FileInputStreamTest {

    public static void main(String[] args) {

        // 1 文本文件使用字节流会出现乱码 →中文
        FileInputStream fis = null;
        try {
            File file = new File("toHi.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;
            // 注意，这里中文读取使用字节流byte读取会出现乱码
            // 因为5个字节的话，中文有可能是3个字节，长度为5可能会出现截断编码出错
            // 所以不建议文本文件使用字节流
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
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
        }

        // 2 通过字节流实现对图片的复制

        FileInputStream fisPic = null;
        FileOutputStream fosPic = null;

        try {
            File srcPicFile = new File("srcPic.jpg");
            File destPicFile = new File("destPic.jpg");

            fisPic = new FileInputStream(srcPicFile);
            fosPic = new FileOutputStream(destPicFile);
            byte[] picBuffer = new byte[10];
            int len;
            while ((len = fisPic.read(picBuffer)) != -1) {
                fosPic.write(picBuffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fisPic != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fosPic != null) {
                try {
                    fosPic.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 测试一下 copyFile() 拷贝任一文件方法并且输出速度

        long start = System.currentTimeMillis();
        String srcPath = "/Users/Chihokyo/Code/javava/advanced/Advanced/srcPic.jpg";
        String destPath = "/Users/Chihokyo/Code/javava/advanced/Advanced/destPic.jpg";

        copyFile(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("成功复制！复制操作花费的时间为: " + (end - start));

    }

    // 3 指定路径下对文件的复制
    public static void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            // 这里虽然是字节流
            // 但是如果是字符流，进行整体复制操作的话，是不可能出现乱码的。
            // 如果只是复制，那么就2个都可以。
            // 如果要输出到屏幕上，字节是不行的。字符而会出错。所以要慎重考虑。
            // 是字符还是字节，是输出，还是只是单纯的复制

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

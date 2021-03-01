package com.io.exer;

import java.io.*;

/**
 * 关于缓冲流的一些课后练习 
 * 1 图片的加密
 * 2 图片的解密
 */
public class FileStreamDemo {
    public static void main(String[] args) {   

        System.out.println("加密图片操作开始...");
        encryptPic();
        System.out.println("加密成功结束!");

        System.out.println("解密图片操作开始...");
        decryptPic();
        System.out.println("解密成功结束!");
    }

    /**
     * 图片加密操作
     */
    public static void encryptPic() {
        // FileInputStream fis = new FileInputStream(new File("srcPic.jpg"));
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("srcPic.jpg");
            fos = new FileOutputStream("encryptPic.jpg");
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                // 因为是加密操作，所以要实现字节数据的修改
                // for (byte b : buffer) {
                // b = (byte) (b ^ 5);
                // } NG 因为这样并没有修改buffer本身
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

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

    public static void decryptPic() {

        // FileInputStream fis = new FileInputStream(new File("srcPic.jpg"));
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("encryptPic.jpg");
            fos = new FileOutputStream("decryptPic.jpg");
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    // 异或操作2次，就会得到原来的数值
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

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

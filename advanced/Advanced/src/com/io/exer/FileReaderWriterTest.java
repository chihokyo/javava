package com.io.exer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 一 流的分类 
 *  1 操作数据单位 字节流 字符流 
 *  2 数据流向 输入流 输出流 
 *  3 流的角色 节点流 处理流
 * 
 * 二 流的体系结构 抽象基类 
 *      InputStream 
 *      OutputStream 
 *      Reader 
 *      Writer 
 * 文件流 
 *      FileInputStream
 *      FileOutputStream 
 *      FileReader 
 *      FileWriter
 * 
 */
public class FileReaderWriterTest {
    public static void main(String[] args) throws IOException {
        // 有一个注意的点就是 工程目录路径 VS Package路径的问题
        File file = new File("hello.txt"); // main方法的路径是 工程的
        // File file = new File("hello"); // 非main方法的路径是 Package的
        System.out.println(file.getAbsolutePath());

        System.out.println("*******获取hello文件 输出到控制台");

        // 1 实例化File对象，指名要操作的文件
        File fileHi = new File("hi.txt");
        System.out.println(fileHi.getAbsolutePath());
        // 2 提供具体的流
        FileReader f = new FileReader(fileHi);

        // 方法一
        // 3 开始读取数据
        // int data;
        // try {
        //     data = f.read();
        //     while (data != -1) {
        //         System.out.print((char)data);
        //         data = f.read();
        //     }
        //     // 切记关闭流
        //     f.close();

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // 方法二
        // 但是需要把上面的 Throws 改成更大的  
        // FileNotFoundException → IOException
        // int data;
        // data = f.read();
        // while (data != -1) {
        //     System.out.print((char)data);
        //     data = f.read();
        // }
        // f.close();

        // 方法二 略优化 语法修改
        int data;
        data = f.read();
        while ((data = f.read()) != -1) {
            System.out.print((char)data);
            data = f.read();
        }
        f.close();

        // 其实用throws不太好。因为会造成流没有关闭，影响了效率。
        // 所以最好的方法就是 try-catch方法

        // 4 正常使用书写的方法
        // 变量要写在外面
        FileReader fr = null;
        try {
            // 1 新建文件对象
            File fileNormal = new File("hi.txt");
            // 2 新建流
            fr =  new FileReader(fileNormal);
            // 3 文件数据
            int data2;
            // 4 读取文件并保存到数据变量
            data2 = fr.read();
            // 5 如果不为-1 没读到头
            while (data2 != -1) {
                System.out.println((char)data2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6 这里要做 是否为空关闭流的操作
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

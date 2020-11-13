package com.io.exer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 关于Writer写出的操作
 * 从内存中写出数据到硬盘的文件里。
 *     说明：
 *     1. 输出操作，对应的File可以不存在的。并不会报异常
 *     2.
 *          File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
 *          File对应的硬盘中的文件如果存在：
 *                 如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
 *                 如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容
 */
public class FileReaderWriterTest3 {
    public static void main(String[] args) {
        // 1.新建文件对象 → 就是你想从内存拿出来并且写进入到的文件
        File file = new File("toHi.txt");
        // 2.新建流
        FileWriter fw;
        try {
            // fw = new FileWriter(file);
            fw = new FileWriter(file, true);
            // 解说
            // public FileWriter(File file, boolean append)
            // append → 是否追加 false 不在末尾追加
            // append → 是否追加 true 在末尾追加
            // 3.写出的操作
            fw.write("I love YOU\n");
            fw.write("Thanks!\n");
            // 4.关闭流
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}

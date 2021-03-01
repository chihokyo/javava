package com.file.exer;

import java.io.File;
import java.io.FilenameFilter;

/**
 * File类的练习2 
 * 判断指定目录下是否有后缀名为.jpg的文件 如果有，就输出相应的文件名
 */
public class FileDemo2 {
    public static void main(String[] args) {

        // 方法1 list() → forEach → endsWith
        // 思路 使用list()方法输出所有的目录
        File srcFile = new File("/Users/Chihokyo/Code/"); 
        String[] srcFileList = srcFile.list();
        // 查询遍历是否有后缀为.jpg的 输出
        for (String fileName : srcFileList) {
            if (fileName.endsWith("jpg")) {
                System.out.println(fileName);
            }
        }

System.out.println("******华丽的分割线*******");

        // 方法2 listFiles() → forEach → getName → endsWith
        File srcFile2 = new File("/Users/Chihokyo/Code/"); 
        File[] fileList = srcFile2.listFiles();
        for (File file : fileList) {
            if (file.getName().endsWith("txt")) {
                System.out.println(file.getName());
            }
        }

System.out.println("******华丽的分割线*******");

         // 方法3 
        //  File类提供的文件过滤器方法
        // public File[] listFiles(FileFilter filter)
        // public String[] list(FilenameFilter filter)

        File srcFile3 = new File("/Users/Chihokyo/Code/javava");

        File[] subFiles = srcFile3.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });
        for (File file : subFiles) {
            // 打印绝对路径
            System.out.println(file.getAbsolutePath());
        }


    }
}

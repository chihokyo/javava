package com.file.exer;

import java.io.File;

/**
 * File类的练习3 遍历指定目录的所有文件名称，包括子目录中的所有文件 
 *      拓展1 并计算目录占用空间的大小 
 *      拓展2 删除指定文件目录以及旗下所有文件
 */
public class FileDemo3 {
    public static void main(String[] args) {
        // 方法1 递归
        File dir = new File("/Users/Chihokyo/Code/");
        printDir(dir);

    }

    // 方法递归
    public static void printDir(File dir) {
        File[] subFiles = dir.listFiles();
        for (File file : subFiles) {
            if (file.isDirectory()) {
                // 递归实现遍历文件目录
                printDir(file);
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    // 方法2 循环 + 递归
    // 使用2个方法 第一个打印第一层目录，第二个通过递归进行循环
    // 列出file 打印一级目录
    public void listFirstFiles(File file) {
        if (file.isDirectory()) {
            String[] all = file.list();
            for (String s : all) {
                System.out.println(s);
            }
        } else {
            System.out.println(file + "文件");
        }
    }

    // 列出file 一级之后的下层目录
    // 相当于这一个方法就是剥一层
    public void listAllSubFiles(File file) {
        if (file.isFile()) {
            System.out.println(file);
        } else {
            // 如果不是文件 那这里就是一个目录
            File[] all = file.listFiles();
            // 如果all[i] 是文件直接打印，
            // 如果all[i] 是目录的话接着获取下一级 在接着
            for (File f : all) {
                listAllSubFiles(f); // 递归
            }
        }
    }

    /*********** 求指定目录所在空间的大小*********** */

    public long getDirSize(File file) {
        // 1.判断如果是文件 直接获取length
        // 2.如果是目录 把它的下一级所有的大小加起来就是总共的大小

        long size = 0;
        if (file.isFile()) {
            size += file.length();
        } else {
            // 获取file下一级
            File[] allList = file.listFiles();
            for (File f : allList) {
                // 计算积累大小
                size += getDirSize(f);
            }
        }

        return size;
    }

    /*********** 删除指定的目录*********** */
    // 思路
    // 如果file是文件，直接删除
    // 如果file是目录，先删除下一级，然后删除自己
    public void deleteDir(File file) {

        if (file.isDirectory()) {
            File[] all = file.listFiles();
            for (File f : all) {
                // f.delete() NG 这里不能直接删除，而是需要进行递归循环删除
                // 因为这个f有可能也是一个目录 这里的f代表的是下一级，而不是本身
                deleteDir(f);
            }
        }
        file.delete();
    }

}

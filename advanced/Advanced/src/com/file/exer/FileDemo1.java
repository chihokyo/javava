package com.file.exer;

import java.io.File;
import java.io.IOException;

/**
 * File类的练习1 
 * 
 * 创建一个与file同目录下的另一个文件，命名为haha2.txt
 * 
 */
public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        // 虽然haha1并没有，但也不影响 destFile 创建成功
        File file = new File("/Users/Chihokyo/Code/javava/haha1.txt");
        File destFile = new File(file.getParent(), "haha2.txt");
        boolean newFile = destFile.createNewFile();
        if (newFile) {
            System.out.println("haha2创建成功");
        }

    }
}

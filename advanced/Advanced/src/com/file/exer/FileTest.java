package com.file.exer;

import java.io.File;
import java.sql.Date;

/**
 * File类使用
 * 
 * 1. File类是一个对象，代表一个文件或者一个文件目录。（俗称文件夹） 
 * 2. File类声明在java.io下面 
 *      File(Fileparent,String child) 
 *      File(String pathname) 
 *      File(String parent, String child)
 * 3    相对路径：相较于某个路径下，指明的路径。 
 *      绝对路径：包含盘符在内的文件或文件目录的路径
 * 
 * 4 关于路径分隔符
 *      windows:\\
 *      unix:/
 *      [java有一个常量，匹配所有系统分隔符]
 * 5 基本使用方法
 * 
 */
public class FileTest {
    public static void main(String[] args) {
        // 如何创建File类的实例 → 这样输出的只是一个路径的对象，并不是文件本身。
        // 构造器1
        File file1 = new File("file1.txt"); // file1.txt
        File file2 = new File("/Users/Chihokyo/Code");
        System.out.println(file1);
        System.out.println(file2); // /Users/Chihokyo/Code
        // 构造器2 通过父为路径 子为文件or路径
        File file3 = new File("/Users/Chihokyo/Code", "javava");
        System.out.println(file3); // /Users/Chihokyo/Code/javava
        // 构造器3 通过父为对象 子是文件
        File file4 = new File(file3, "file4.txt");
        System.out.println(file4); // /Users/Chihokyo/Code/javava/file4.txt

        /**
         * public String getAbsolutePath()：获取绝对路径
         * public String getPath() ：获取路径
         * public String getName() ：获取名称
         * public String getParent()：获取上层文件目录路径。若无，返回null
         * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
         * public long lastModified() ：获取最后一次的修改时间，毫秒值

        * 如下的两个方法适用于文件目录：
        * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
        * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
        */

        System.out.println("*******华丽的分割线*******");
        System.out.println(file4.getAbsolutePath()); // /Users/Chihokyo/Code/javava/file4.txt
        System.out.println(file4.getPath()); // /Users/Chihokyo/Code/javava/file4.txt
        System.out.println(file4.getName()); // file4.txt
        System.out.println(file4.getParentFile()); // /Users/Chihokyo/Code/javava
        System.out.println(file4.length()); // 39 字节
        System.out.println(file4.lastModified()); // 1605107212857
        System.out.println(new Date(file4.lastModified())); // 2020-11-12
        System.out.println("*******华丽的分割线*******");

        // 文件目录相关1 list() 获取1层文件和文件名
        File file5 = new File("/Users/Chihokyo/Code");
        // 如果不存在文件名会报错 java.lang.NullPointerException:
        String[] fileList = file5.list();
        System.out.println(fileList); 
        for (String string : fileList) {
            System.out.println(string);
        }

        // 文件目录相关2 listFiles() 输出的是绝对路径的字符串
        File[] files = file5.listFiles();
        for ( File f : files) {
            System.out.println(f);
        }

        System.out.println("*******华丽的分割线*******");

        // 文件目录相关2 renameTo 重命名（路径和文件名一起走）
        // file6 和 file7 是否都应该存在？
        // public boolean renameTo(File dest)
        // file1.renameTo(file2) file1必须存在 file2不要存在
        File file6 = new File("/Users/Chihokyo/Code/javava", "file4.txt");
        File file7 = new File("hi.txt");
        boolean result = file6.renameTo(file7);
        System.out.println(result); // true
        System.out.println(file6.getAbsolutePath()); 

       /**
        * public boolean isDirectory()：判断是否是文件目录
        * public boolean isFile() ：判断是否是文件
        * public boolean exists() ：判断是否存在
        * public boolean canRead() ：判断是否可读
        * public boolean canWrite() ：判断是否可写
        * public boolean isHidden() ：判断是否隐藏
        */

        System.out.println(file7.isDirectory());
        System.out.println(file7.isFile());
        System.out.println(file7.exists());
        System.out.println(file7.canRead());
        System.out.println(file7.canWrite());
        System.out.println(file7.isHidden());

        // false
        // true
        // true
        // true
        // true
        // false
        

    }
}

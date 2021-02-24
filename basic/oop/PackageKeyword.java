package oop;
// import java.util.Arrays;
// import java.util.HashMap;

import java.util.*;

/**
 * 一 package 关键字的使用
 * 1 为了更好的实现管理，就提供包的概念
 * 2 使用package 声明类或者接口所属的包 声明在源文件首行（不一定是第1行，只要是整体的第1行（首行就行））
 * 3 包 属于标识符，要遵守标识符规范且要有具体含义(xxxyyyzzz 基本都是小写)官方api可以参考规范案例
 * 4 点结构。（com.xxx.yyy） 每.一次 就是一层文件目录
 * 
 * 补充，同一个包下不能够命名同名结构 （接口 or 类）
 *      不同的包下，可以命名同名的
 * 
 * 
 *  二 import 关键字的使用
 *      导入的意思
 *      1 源文件中显式指定导入指定包的类or接口 
 *      2 声明在package 和 class 的声明之前
 *      3 可以导入多个结构，并列写出
 *      4 也可以使用  import java.util.*; 一次性导入多个
 *      5 定义在 java.lang 下定义的话，则可以省略导入import 属于核心包
 *      6 如果是当前包定义的话，也可以省略
 */

public class PackageKeyword {
    public static void main(String[] args) {
        // 上面导入了包 ↑↑↑↑ import java.util.Arrays;
        String info = Arrays.toString(new int[]{1,2});
        System.out.println(info);
        HashMap map = new HashMap();
        System.out.println(map);

    }
}

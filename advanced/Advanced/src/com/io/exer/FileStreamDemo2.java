package com.io.exer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 关于缓冲流的一些课后练习2 
 * 获取文本上每个字符出现的次数 遍历文本的每一个字符，
 * 字符出现的次数保存在Map中，
 * 将Map的数据写入文件
 */

public class FileStreamDemo2 {
    public static void main(String[] args) {

        System.out.println("统计操作开始");
        countChar();
        System.out.println("统计操作结束");
    }

    /**
     * 思路
     * 1.遍历文本每一个字符
     * 2.字符出现次数存在Map中
     *      Map<Character, Integer> map = new HashMap<Character, Integer>();
     * 3.把Map写入文件
     */

    public static void countChar() {
        FileReader fr = null;
        BufferedWriter bw = null;
        try {
            // 1. 创建Map集合
            Map<Character, Integer> map = new HashMap<>();
            // 2. 遍历每一个字符，和出现次数放到Map里
            // 2-1 输入对象 读取文本每一个字符
            fr = new FileReader(new File("countSrc.txt"));
            int c = 0;
            while ((c = fr.read()) != -1) {
                // 因为读取的是c是int 转换成char
                char ch = (char) c;
                // 判断是否第一次出现
                // 2-2 存储到Map
                if (map.get(ch) == null) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }
            // 3.输出对象
            bw = new BufferedWriter(new FileWriter("countDest.txt"));
            // 3-1 上面Map进行拆分成k-w形式
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
            // 3-2 遍历并且判断
            for (Entry<Character, Integer> entry : entrySet) {
                switch (entry.getKey()) {
                    case ' ':
                        bw.write("空格=" + entry.getValue());
                        break;
                    case '\t':
                        bw.write("tab=" + entry.getValue());
                        break;
                    case '\r':
                        bw.write("enter=" + entry.getValue());
                        break;
                    case '\n':
                        bw.write("newline=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + entry.getValue());
                        break;
                }
                // 【注意！这里不能忘记】
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4 关闭流
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

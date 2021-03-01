package com.io.exer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * read重载方法()
 * 
 * public int read(char cbuf[]) throws IOException {
    return read(cbuf, 0, cbuf.length);
    }
    这里要注意的就是长度的问题。数组的长度并非就是数据本身读取的长度。
    可能数据只是读取了1个，但是这个时候计算长度还是5
    那么就有可能拿到错误的数据。这个时候就要有一个变量专门来存放数据本身的长度
    进行遍历就不会出错。
 */
public class FileReaderWriterTest2 {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            File file = new File("hi.txt");
            fr = new FileReader(file);
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // NG 写法 因为数组这个时候就是固定的为5
                // for (int i = 0; i < cbuf.length; i++) {
                //     System.out.print(cbuf[i]);
                // }
                // OK-1 这个时候长度应该是读取数据的长度
                // for (int i = 0; i < len; i++) {
                //     System.out.print(cbuf[i]);
                // }
                // NG 如果按照上面的NG  那么这个也是NG
                // String str = new String(cbuf);
                // System.out.println(str);
                // OK-2 如果按照上面的NG  这样输出才是对的
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

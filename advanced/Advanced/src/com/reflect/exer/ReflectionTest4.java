package com.reflect.exer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  ClassLoader类加载器加载配置文件 Properties
 */
public class ReflectionTest4 {
    public static void main(String[] args) {

        // 读取方式1
        readProps1();
        // 读取方式2
        readProps2();
    }

    public static void readProps1() {
        Properties pros = new Properties();
        FileInputStream fis = null;
        try {
            // 读取方式一  
            // 此时文件默认在当前module下（其实如果指定是绝对路径其实也可以指定的）
            fis = new FileInputStream("data.properties");
            pros.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String user = pros.getProperty("USER");
        String pass = pros.getProperty("PASS");

        System.out.println("user=" + user + "pass=" + pass);
    }

    public static void readProps2() {

        Properties pros = new Properties();
        ClassLoader classLoader = ReflectionTest4.class.getClassLoader();
        // 读取方式二
        // 此时文件默认在当前module的src下
        InputStream is = classLoader.getResourceAsStream("data1.properties");
        try {
            pros.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String user = pros.getProperty("USER");
        String pass = pros.getProperty("PASS");

        System.out.println("user=" + user + "pass=" + pass);
    }
}

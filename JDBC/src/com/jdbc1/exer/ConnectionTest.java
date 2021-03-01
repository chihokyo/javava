package com.jdbc1.exer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    public static void main(String[] args) throws Exception {

        // // 方式一，最原始
        // Driver driver = new com.mysql.jdbc.Driver();

        // // 协议（jdbc:mysql:）+IP地址+端口号+数据库名称
        // // 协议（jdbc:mysql:）主协议 子协议
        // String url = "jdbc:mysql://localhost:3306/C2blog";
        // Properties info = new Properties();
        // info.setProperty("user", "root");
        // info.setProperty("password", "root");
        // System.out.println(info);
        // Connection conn = driver.connect(url, info);
        // System.out.println(conn);


        // // 方式二，最原始 不需要第三方API 具备可移植性 比如 new com.mysql.jdbc.Driver();
        // // 1. 获取Driver类实现类对象，使用反射
        // Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        // Driver driver =  (Driver) clazz.getConstructor().newInstance();
        // System.out.println(driver);
        // // 2.提供数据库
        // String url = "jdbc:mysql://localhost:3306/C2blog";
        // // 3. 配置类
        // Properties info = new Properties();
		// info.setProperty("user", "root");
        // info.setProperty("password", "root");
        // // 4.获取连接
        // Connection conn = driver.connect(url, info);
        // System.out.println(conn);
        
        // // 方式三 使用DriverManager替换Driver
        // // 1. 获取Driver类实现类对象，使用反射
        // Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        // Driver driver = (Driver) clazz.getConstructor().newInstance();
        // // 2. 连接数据库基本信息
        // String url = "jdbc:mysql://localhost:3306/C2blog";
        // String user = "root";
        // String password = "root";
        // // 3. 注册驱动
        // DriverManager.registerDriver(driver);
        // // 4.获取连接
        // Connection conn = DriverManager.getConnection(url, user, password);
        // System.out.println(conn);

        // // 方式四 可以只是加载驱动，不用显示注册信息
        // // 1. 连接数据库基本信息
        // String url = "jdbc:mysql://localhost:3306/C2blog";
        // String user = "root";
        // String password = "root";
        // // 2 加载Driver
        // Class.forName("com.mysql.jdbc.Driver");
        // // 3 获取连接
        // Connection conn = DriverManager.getConnection(url, user, password);
        // System.out.println(conn);


        // 方式五 最终版本
        // 好处
        // 1 实现接口 2 如果需要修改配置文件信息，可以避免程序重新打包
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 2 加载驱动
        Class.forName(driverClass);
        // 3 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);


    }
}

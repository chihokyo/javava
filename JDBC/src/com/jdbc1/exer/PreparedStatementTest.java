package com.jdbc1.exer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;


import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 
 * @Description 演示使用 PreparedStatement 替换 掉 Statement 解决SQL注入问题
 * 好处1 安全
 * 好处2 可以快速执行批量操作
 * @author chin
 * @version
 */
public class PreparedStatementTest {
    @Test
    public void testLogin() {

        // Scanner scanner = new Scanner(System.in);

        // System.out.println("请输入用户名: ");
        // String user = scanner.nextLine();
        // System.out.println("请输入密码: ");
        // String pass = scanner.nextLine();
        String user = "AA";
        String pass = "123456";

        // SELECT user,password FROM user_table WHERE user = '1' or ' AND password =' =1
        // or '1' = '1';
        String sql = "SELECT user,password FROM user_table WHERE user = ? and password = ?";

        User returnUser = getInstance(User.class, sql, user, pass);

        if (returnUser != null) {
            System.out.println("OK");
        } else {
            System.out.println("NOOO!");
        }

        // scanner.close();

    }

    /**
     * 
     * @Description 针对不同表的通用查询操作。返回1条记录
     * @author chin
     * @param clazz 表
     * @param sql   sql语句
     * @param Object  参数列表
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // 1. 连接数据库
            conn = JDBCUtils.getConnection();
            // 2. pre化sql语句
            ps = (PreparedStatement) conn.prepareStatement(sql);
            // 3. 遍历所有参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4. 执行语句
            rs = ps.executeQuery();
            // 5. 获取结果集 元数据 ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 6. 获取列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                // 这里获取了很多条数据
                T t = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取列的值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给指定t对象指定的 columnName属性，赋值为 columnValue 通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }
}

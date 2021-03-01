package com.jdbc1.exer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 使用PreparedStatement针对不同表通用查询的操作
 * 
 * @author chin
 * @version
 */
public class PreparedStatementForQuery {

    @Test

    public void testGetForList() {
        String sql = "select id, name, email from customers where id < ?";
        List<Customer> list = getForList(Customer.class, sql, 12);
        list.forEach(System.out::println);
    }

    /**
     * 针对不同表的通用查询操作，返回表中的多条记录
     * 
     * @author chin
     * @version
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // 要注意！！是一个泛型列表
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                // 这里要注意！！
                T t = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }

    @Test
    public void testGetInstance() {

        String sql = "SELECT id, name, email FROM customers WHERE id = ?";
        Customer cust = getInstance(Customer.class, sql, 2);
        System.out.println(cust);

        String sql2 = "select order_id orderId, order_name orderName from `order` where order_id = ?";
        Order or = getInstance(Order.class, sql2, 4);
        System.out.println(or);

    }

    /**
     * 针对不同表的通用查询操作，返回表中的1条记录
     * 
     * @param clazz 类
     * @param sql   sql语句
     * @param args  参数列表
     * @author chin
     * @version
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // 判断是否有数据
            if (rs.next()) {
                // 新建对象
                T t = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取对象值
                    Object columnValue = rs.getObject(i + 1);
                    // 获取record
                    String columnName = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnName);
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

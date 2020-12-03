package com.jdbc1.exer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 
 * @Description 针对 Order 的查询操作
 * @author chin
 * @version
 */
public class OrderForQuery {
     /**
     * 针对表的字段名和类属性名不同的情况
     * 1 必须声明sql时候，实用类的属性来命名字段的别名
     * 2 使用 ResultSetMetaData 时候，需要使用 getColumnLabel 来替换 getColumnName 获取别名
     *  如果sql没有给定别名 那么 getColumnLabel 就是别名
     * 
     * 意思很简单， getColumnLabel 能获取别名还能获取列名
     * 但是getColumnName 只能获取列名 所以推荐使用的是 getColumnLabel！
     */
    
    @Test
    public void testOrderForQuery() {
        // 这里注意看SQL语句，使用了别名作为连接字段和对象属性不一样的情况
        // getColumnLabel就是获取了别名 orderId， orderName...
        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order); // { orderId='1', orderName='AA', orderDate='2010-03-04'}
    }

    /**
     * 
     * 【通用版本】Order表连接方式
     * 
     * @author chin
     * @version
     */
    public Order orderForQuery(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 获取结果集
            rs = ps.executeQuery();
            // 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    // 获取每个列的值
                    Object columnValue = rs.getObject(i + 1);
                    // 通过 ResultSetMetaData
                    // 获取列的列名 getColumnName
                    // 获取列的【别名】 columnLabel 推荐！！
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 通过反射
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, columnValue);
                }
                return order;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }

    /**
     * 
     * Order表普通的连接方式
     * 
     * @author chin
     * @version
     */
    @Test
    public void testOrderQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT order_id, order_name, order_date FROM `order` WHERE order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = (int) rs.getObject(1);
                String orderName = (String) rs.getObject(2);
                Date orderDate = (Date) rs.getObject(3);

                Order order = new Order(orderId, orderName, orderDate);

                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

    }
}

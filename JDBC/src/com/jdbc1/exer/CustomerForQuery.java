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
 * @Description 针对 Customer 的查询操作
 * @author chin
 * @version
 */
public class CustomerForQuery {

    // *****测试*****

    @Test
    public void testQueryForCustomer() {
        String sql = "SELECT id, name, email, birth FROM customers WHERE id = ?";
        Customer cust = queryForCustomer(sql, 10);
        System.out.println(cust); // { id='10', name='tom4', email='zhoujl@sina.com', birth='1979-11-15'}

        String sql2 = "SELECT id, name, email FROM customers WHERE name = ?";

        Customer cust2 = queryForCustomer(sql2, "amy");
        System.out.println(cust2); // { id='1', name='amy', email='wf@126.com', birth='null'}

    }

    public Customer queryForCustomer(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1 连接数据库
            conn = JDBCUtils.getConnection();
            // 2 预编译
            ps = conn.prepareStatement(sql);
            // 3 参数列表
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4 获得结果集
            rs = ps.executeQuery();
            // 元数据是什么呢，就是修饰现有数据的数据
            // String name = "Tom" 其实这条数据真实只有Tom 那么 String name 就是元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 4-1 通过结果集获得列数（也就是结果） 一个数据多少列这种，比如取出来一个student，那么name id啥的就是列
            int columnCount = rsmd.getColumnCount();

            // 5 遍历结果集 这里注意next使用 既包含判断又有指针下移
            // 如果不用if 就是while 循环取值 直到null
            if (rs.next()) {

                // 这里是特定的
                Customer cust = new Customer();

                // 这里有个区分很重要
                // rs是一个对象是一个结果集
                // 而元数据包裹起来的rsmd是具体取值的地方
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值 必须要 + 1 数据库从1开始
                    Object columnVale = rs.getObject(i + 1);

                    // 获取列名 因为列表比如id 其实就是java类的属性
                    String columnName = rsmd.getColumnName(i + 1);

                    // 使用的反射 把对象数据 设置 成 对象属性
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(cust, columnVale);
                }

                return cust;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        // 没有就是 null 不能少啊
        return null;
    }

    @Test
    public void testQuery() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT id, name, email, birth FROM customers WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);
            // 注意这里不是 execute 而是 executeQuery
            // 因为这里是查询操作，获得是一个结果集 而不是执行一条结果语句
            rs = ps.executeQuery();
            // 判断是否有下一条数据，是的话就返回 true 并下移指针
            // 如果是 false 指针就不会下移
            // 这里的next() 有俩个功能，相当于以前的hasnext和next
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);

                // 方式1
                System.out.println("id=" + id + ",name=" + name + ",email=" + email + ",birth=" + birth);

                // 方式2 对象数组
                Object[] data = new Object[] { id, name, email, birth };
                System.out.println(data);
                // 方式3 封装成对象输出，（推荐）
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                // id=1,name=amy,email=wf@126.com,birth=2010-02-02
                // [Ljava.lang.Object;@71318ec4
                // { id='1', name='amy', email='wf@126.com', birth='2010-02-02'}

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
    }
}

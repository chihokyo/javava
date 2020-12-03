package com.jdbc1.exer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

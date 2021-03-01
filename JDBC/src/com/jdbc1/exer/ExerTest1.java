package com.jdbc1.exer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 练习1 插入一条数据给 Customers
 */
public class ExerTest1 {

    @Test
    public void testCommonUpdate() {
        String name = "TOM";
        String email = "test@gmail.com";
        String birth = "2020-12-09";

        String sql = "insert into customers(name, email,birth)values(?,?,?)";
        int result = commonUpdate(sql, name, email, birth);
        if (result > 0) {
            System.out.println("OKKK");
        } else {
            System.out.println("No!!");
        }

    }

    /**
     * 通用的增删改操作
     */
    public int commonUpdate(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // execute()
            // 执行 如果是查询操作，有返回结果则是true 如果是增删改操作就是false 和返回结果有无是无关的

            // 方式1 execute 方式2 executeUpdate
            // return ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }

        return 0;
    }
}

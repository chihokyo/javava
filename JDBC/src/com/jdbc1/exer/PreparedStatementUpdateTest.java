package com.jdbc1.exer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 
 * @Description 演示使用 PreparedStatement 替换 掉 Statement 实现对数据表的增删改查
 * @author chin
 * @version
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommonUpdate() {

        // 测试1，修改 order_id 为 2 order_name 的为DD
        String sql = "UPDATE `order` SET order_name = ? WHERE order_id = ?";
        commonUpdate(sql, "DD", "2");

        // 测试2，插入一条数据
        // 因为order是一个关键字，所以需要 ` ` 标识。
        String sql2 = "INSERT INTO `order`(order_id, order_name, order_date)VALUES(?,?,?)";
        commonUpdate(sql2, 3, "NN", "2020-11-11");

        // 测试3，删除一条数据
        String sql3 = "DELETE FROM `order` WHERE order_id = ?";
        commonUpdate(sql3, 2);

    }

    /**
     * @Description 通用的增删改查操作
     */
    public void commonUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // 1. 连接
            conn = JDBCUtils.getConnection();
            // 2 预编译 返回 PreparedStatement 实例
            ps = conn.prepareStatement(sql);
            // 3. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4. 执行
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}

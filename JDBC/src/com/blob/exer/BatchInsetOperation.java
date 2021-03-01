package com.blob.exer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;
/**
 * 使用 PreparedStatement 实现批量操作
 * update delete 本身就具有批量操作的效果。
 * 比如一次性设置多个数据，一次性删除多个数据
 * 比如向一个表中插入2W条数据的话。
 * 1 使用Statement
 * 
 * Connection conn = JDBCUtils.getConnection();
    Statement st = conn.createStatement();
    for (int i = 0; i <= 20000; i++) {
        String sql = "insert statement";
        st.execute(sql)
    }

 * 2 PreparedStatement进行遍历
 * 3 使用 batch() PreparedStatement进行遍历
 * 4 设置非自动提交事务的操作。PreparedStatement进行遍历
 */
public class BatchInsetOperation {
    // 批量插入方法1 使用 PreparedStatement进行遍历
    @Test
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)value(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {

                ps.setObject(1, "name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("spend:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    /**
     * 批量插入方法2 1 addBatch() 2 executeBatch() 3 clearBatch() 2
     * mysql默认是关闭批处理操作的，需要开启一个参数进行开启批处理操作。 
     * ?rewriteBatchedStatement=true 3
     * 使用更新的musql 驱动
     */
    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)value(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <= 10000; i++) {
                ps.setObject(1, "name_" + i);
                // 1 积攒sql
                ps.addBatch();
                if (i % 500 == 0) {
                    // 2 积攒到500的倍数开始执行
                    ps.executeBatch();
                    // 3 记得清空
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("spend:" + (end - start)); // spend:8978

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    /**
     * 批量插入方法3 
     * 设置不允许自动提交数据 
     * 上面的情况就是写一条自动提交commit一条
     */
    @Test
    public void name() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name)value(?)";
            ps = conn.prepareStatement(sql);
            // 【重点】
            conn.setAutoCommit(false);
            for (int i = 0; i <= 10000; i++) {
                ps.setObject(1, "name_" + i);
                // 1 积攒sql
                ps.addBatch();
                if (i % 500 == 0) {
                    // 2 积攒到500的倍数开始执行
                    ps.executeBatch();
                    // 3 记得清空
                    ps.clearBatch();
                }
            }

            // 【重点】
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("spend:" + (end - start)); // spend:2753
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}

package com.transaction.exer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 关于数据库事务操作。 事务：一组逻辑操作单元,使数据从一种状态变换到另一种状态。 
 *              > 一组逻辑操作单元：一个或多个DML操作。
 * 
 * 2.事务处理的原则：保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
 * 当在一个事务中执行多个操作时，要么所有的事务都被提交(commit)，那么这些修改就永久地保存
 * 下来；要么数据库管理系统将放弃所作的所有修改，整个事务回滚(rollback)到最初状态。
 * 
 * 3.数据一旦提交，就不可回滚
 * 
 * 4.哪些操作会导致数据的自动提交？ 
 * >DDL（Data Definition Language）操作一旦执行，都会自动提交。 
 * >set autocommit = false 对DDL操作失效 
 * >DML（Data Manipulation
 * Language）默认情况下，一旦执行，就会自动提交。 
 * >我们可以通过set autocommit = false的方式取消DML操作的自动提交。
 * >默认在关闭连接时，会自动的提交数据 
 * 顺便补充一个概念是 DCL(Data Control Language) 数据控制语言 (Data Control Language)
 * 在SQL语言中，是一种可对数据访问权进行控制的指令，它可以控制特定用户账户对数据表、查看表、存储程序、用户自定义函数等数据库对象的控制权。
 * 由 GRANT
 * 和 REVOKE 两个指令组成。
 */
public class TransactionTest1 {
    
    @Test
    public void testUpdate() {

        String sql1 = "update user_table set balance = balance - 100 where user = ?";
		update(sql1, "AA");
		
		//模拟网络异常
		System.out.println(10 / 0);
		
		String sql2 = "update user_table set balance = balance + 100 where user = ?";
		update(sql2, "BB");
		
        System.out.println("转账成功");
        // 不仅仅没有成功，反而AA少了100，但是BB却没有增加 这次转账是错误的失败的

    }

    /**********没有考虑数据库事务操作的情况 */
    // 通用的增删改操作
    public int update(String sql, Object... args) {
        Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1.获取数据库的连接
			conn = JDBCUtils.getConnection();
			// 2.预编译sql语句，返回PreparedStatement的实例
			ps = conn.prepareStatement(sql);
			// 3.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);// 小心参数声明错误！！
			}
			// 4.执行
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			//修改其为自动提交数据
			//主要针对于使用数据库连接池的使用
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// 5.资源的关闭
			JDBCUtils.closeResource(conn, ps);

		}
		return 0;
    }

    @Test
    public void testUpdateWithTrans() {
        Connection conn = null;
        try {
            // 正常操作
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit()); // 因为是true 所以要取消操作
            // 1 取消事务提交
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");
            // 模拟网络异常
            // System.out.println(10/0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");

            System.out.println("转账成功");
            // 2 提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 3 超级重要 如果发生异常就要回滚数据
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 修改其为自动提交数据，主要是针对于数据库连接池的使用。
            JDBCUtils.closeResource(conn, null);
        }
    }
    /**********考虑数据库事务操作的情况 */
    // 通用的增删改操作
    public int update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 这里资源要注意的就是null 因为是从外面传入的，所以不能随便关闭
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }
}

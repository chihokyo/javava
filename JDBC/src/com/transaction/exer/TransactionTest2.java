package com.transaction.exer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;
/**
 * 事务操作的隔离性 
 * 模拟2个操作，一个进行查询，一个进行修改。 
 * 从而实现对隔离性的理解和设置。
 */
public class TransactionTest2 {

    @Test
    public void testTransactionSelect() throws Exception {

        Connection conn = JDBCUtils.getConnection();
        // 获取当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation()); // 4 TRANSACTION_REPEATABLE_READ
        // 设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        // 取消自动提交数据
        conn.setAutoCommit(false);
        String sql = "SELECT user, password, balance FROM user_table WHERE user = ?";
        User user = getInstance(conn, User.class, sql, "CC");
        System.out.println(user); // { user='CC', password='abcd', balance='2000'}
    }

    @Test
    public void testTransactionUpdate() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql = "update user_table set balance = ? where user = ?";
        commonUpdate(conn, sql, 5000, "CC");
        // 虽然这里修改时5000 但是隔离级别如果是1 的情况下。即使改变了。上面获取的是5000.这种获取5000是不对的，因为并没有提交
        // 如果设置成了1 以上的级别，就是这里无论怎么修改，只要在提交前，上面获取的都是4000的数据库的真实数据
        Thread.sleep(15000);
        System.out.println("修改结束");
    }

    /**
     * 通用的修改操作 返回数据表中一条记录 考虑到了事务
     */
    public int commonUpdate(Connection conn, String sql, Object... args) {
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

    /**
     * 通用的查询操作
     */
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args) {
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
            if (rs.next()) {
                T t = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

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

/**
 * User类 用于bean一下便于上面操作数据库
 */
class User {
    private String user;
    private String password;
    private int balance;

    public User() {
        super();
    }

    public User(String user, String password, int balance) {
        this.user = user;
        this.password = password;
        this.balance = balance;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" + " user='" + getUser() + "'" + ", password='" + getPassword() + "'" + ", balance='" + getBalance()
                + "'" + "}";
    }

}
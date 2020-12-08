package com.DAO.exer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;

import com.jdbc.utils.JDBCUtils;

/**
 * @param <T>
 * @Description 封装表的各种通用操作。使用的是抽象的类。 这样就是不能被实例化，只能被调用（类似于工具）
 */
@SuppressWarnings("unchecked")
public abstract class BaseDAO<T> {


    // 优化。因为在实现类的时候可以发现。
    // Customer customer = getInstance(conn, Customer.class, sql, id);
    // 这种写的是没有意义的，因为肯定是只能是 Customer.class 类 
    // 所以要写一个获取当前BaseDAO子类继承的父类中的泛型
    // 写的位置肯定要在new之前 那就是构造器 或者是内部{}
    private Class<T> clazz = null;
    // 在这里使用是一旦 下面的子类开始new 的时候 这里的方法就会执行，不是静态的是因为
    // 上面的 private Class<T> clazz = null; 就不是静态 先于这个加载肯定会报错
    
    
    // public BaseDAO() {
    //     Type getGenericSuperclass = this.getClass().getGenericSuperclass();
    //     ParameterizedType paramType = (ParameterizedType) getGenericSuperclass;
    //     Type[] typeArguments = paramType.getActualTypeArguments(); // 获取父类的泛型参数（数组
    //     clazz = (Class<T>) typeArguments[0];
    // }
        
    {
        Type getGenericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) getGenericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments(); // 获取父类的泛型参数（数组
        clazz = (Class<T>) typeArguments[0];
    }

    /**
     * 1. 通用的增删改操作
     * 
     */
    public int commonUpdate(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1. 预编译sql语句 返回ps实例
            ps = conn.prepareStatement(sql);
            // 2. 设置填充位
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 3. 执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.资源的关闭（记得 conn 是哪里传入哪里关闭，这里不关闭）
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    /**
     * 2. 通用的查询操作【返回一条】
     */

    public T getInstance(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * 3. 通用的查询操作【返回多条】
     */
    public List<T> getForList(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.getConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLable = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLable);
                    field.setAccessible(true);
                    field.set(t, columnValue);

                }
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * 4. 用于特殊的查询（比如带函数）
     */
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}

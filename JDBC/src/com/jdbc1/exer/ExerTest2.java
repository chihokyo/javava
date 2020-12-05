package com.jdbc1.exer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 练习2 学生表 
 * 1 增加一条数据 
 * 2 修改一条数据 
 * 3 删除一条数据
 */
public class ExerTest2 {

    // 1 增加一条数据测试
    @Test
    public void insertStudent() {

        int type = 4;
        String IDCard = "272726262";
        String examCard = "200523164754111";
        String name = "Lee";
        String location = "徐州";
        int grade = 60;
        String sql = "insert into examstudent(type,IDCard,examCard,studentName,location,grade)values(?,?,?,?,?,?)";
        int result = commonUpdate(sql, type, IDCard, examCard, name, location, grade);
        if (result > 0) {
            System.out.println("add success!");
        } else {
            System.out.println("add failed!");
        }

    }

    // 通用的增删改操作
    public int commonUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // ps.execute();
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;

    }

    // 2 根据身份证或者准考证查询一条数据测试
    @Test
    public void testGetInstance() {
        // 选择查询（判断输入正否正确（不区分大小））
        System.out.println("select a type");
        System.out.println("examid a");
        System.out.println("id b");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        // 选择a 准考证
        if (selection.equalsIgnoreCase("a")) {
            System.out.println("请输入准考证号");
            String examCard = scanner.next();
            String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName name,Location location,Grade grade from examstudent where examCard = ?";
            Student student = getInstance(Student.class, sql, examCard);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("输入的准考证有误");
            }

        } else if (selection.equalsIgnoreCase("b")) {
            System.out.println("请输入身份证号");
            String IDCard = scanner.next();
            String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName name,Location location,Grade grade from examstudent where IDCard = ?";
            Student student = getInstance(Student.class, sql, IDCard);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("输入的身份证号有误");
            }
        } else {
            System.out.println("选择有误，重新输入");
        }

        scanner.close();
    }

    // 通用的查询操作
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

    // 3 删除一条信息
    @Test
    public void deleteById() {
        System.out.println("请输入要删除的学生考号");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        String sql = "delete from examstudent where examCard = ?";
        int result = commonUpdate(sql, examCard);
        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("查无此人，请重新输入");
        }
        scanner.close();
    }
}

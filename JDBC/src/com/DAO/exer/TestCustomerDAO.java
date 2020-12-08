package com.DAO.exer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.jdbc.utils.JDBCUtils;

import org.junit.Test;

/**
 * 测试类 测试目标 CustomerDAOImpl
 */

public class TestCustomerDAO {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            // id 是自增的 写什么都可以
            Customer cust = new Customer(1, "haha", "testhaha@test.com", new Date(545454545L));
            dao.insert(conn, cust);
            System.out.println("OK!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testDeleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            dao.deleteById(conn, 13);

            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

    @Test
    public void testUpdateConnectionCustomer() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(18, "贝多芬", "beiduofen@126.com", new Date(453465656L));
            dao.update(conn, cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

    @Test
    public void testGetCustomerById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = dao.getCustomerById(conn, 19);
            System.out.println(cust);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

    @Test
    public void testGetAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Customer> list = dao.getAll(conn);
            list.forEach(System.out::println);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

    @Test
    public void testGetCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = dao.getCount(conn);
            System.out.println("表中的记录数为：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

    @Test
    public void testGetMaxBirth() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println("最大的生日为：" + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);

        }
    }

}

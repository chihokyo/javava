package com.DAO.exer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @Description 此接口用于规范 customers 表的常用操作
 */
public interface CustomerDAO {

    // public abstract void insert(Connection conn, Customer cust){

    // }

    /**
     * 添加数据
     * 
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * 根据id来删除表中记录
     * 
     * @param conn
     * @param id
     */
    void deleteById(Connection conn, int id);

    /**
     * 针对内存中的cust对象 修改制定记录
     * 
     * @param conn
     * @param cust
     */
    void update(Connection conn, Customer cust);

    /**
     * 根据ID查找对象
     * 
     * @param conn
     * @param id
     */
    Customer getCustomerById(Connection conn, int id);

    /**
     * 获取表中所有记录构成的集合
     * 
     * @param conn
     */
    List<Customer> getAll(Connection conn);

    /**
     * 获取表中的数据条目数
     * 
     * @param conn
     */
    Long getCount(Connection conn);

    /**
     * 返回表中birth最大的值
     * 
     * @param conn
     */
    Date getMaxBirth(Connection conn);

}

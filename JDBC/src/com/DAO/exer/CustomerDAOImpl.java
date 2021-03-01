package com.DAO.exer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {

    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "INSERT INTO customers(name,email,birth)VALUES(?,?,?)";
        commonUpdate(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        commonUpdate(conn, sql, id);
    }

    @Override
    public void update(Connection conn, Customer cust) {
        String sql = "UPDATE customers SET name = ?,email = ?,birth = ? WHERE id = ?";
        commonUpdate(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "SELECT id,name,email,birth FROM customers WHERE id = ?";
        Customer customer = getInstance(conn, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "SELECT id,name,email,birth FROM customers";
        List<Customer> list = getForList(conn, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "SELECT count(*) FROM customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "SELECT max(birth) FROM customers";
        return getValue(conn, sql);
    }

}

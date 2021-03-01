package com.blob.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.jdbc.utils.JDBCUtils;
import com.jdbc1.exer.Customer;

import org.junit.Test;
/**
 * 使用PreparedStatement操作Blob类型的数据
 * BLOB Binary Large OBject
 * 二进制大对象 主要是一些视频图像等大的二进制文件
 * 
 * Blob有大小类型限制
 * 如果超过了大小限制，可以去Mysql的myini 进行设置 
 * max_allowed_packet=16M
 */
public class BlobTest {

    // 插入一条图片数据（Blob）到 customers 表
    @Test
    public void insertBlob() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO customers(name, email, birth, photo)VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "tom");
            ps.setObject(2, "test@qq.com");
            ps.setObject(3, "1992-12-01");
            FileInputStream fis = new FileInputStream(new File("/Users/chihokyo/Code/javava/girl.jpeg"));
            ps.setObject(4, fis);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    // 查找 customers 表中的 Blob 类型字段
    @Test
    public void testBlobQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null; // 输出流
        FileOutputStream fos = null; // 输出流
        try {
            // 下面开始操作
            conn = JDBCUtils.getConnection();
            String sql = "SELECT id, name, email, birth, photo FROM customers WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 20);
            rs = ps.executeQuery();
            if (rs.next()) {
                // 方式1
                // int id = rs.getInt(1);
                // String name = rs.getString(2);
                // String email = rs.getString(3);
                // Date birth = rs.getDate(4);

                // 方式2 非按照index 这种无需顺序
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");
                Customer cust = new Customer(id, name, email, birth);
                System.out.println(cust);

                // **********************这里是保存Blob的步骤 */
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("/Users/chihokyo/Code/javava/girl2.jpeg");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JDBCUtils.closeResource(conn, ps, rs);
        }
    }
}

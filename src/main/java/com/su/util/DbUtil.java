package com.su.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接工具
 *
 * @author Sujinran
 */
public class DbUtil {
    //数据库
    private String dbUrl = "jdbc:mysql://localhost:3306/stu02";
    //用户名
    private String dbUserName = "root";
    //密码
    private String dbPassword = "root";
    //mysql驱动
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     *
     * @return
     * @throws Exception
     */
    public Connection getCon() throws Exception {
        //加载驱动
        Class.forName(jdbcName);
        //获得链接
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        //调用getCon()得到数据库连接con
        return con;
    }

    /**
     * 关闭数据库连接
     *
     * @param con 数据库连接
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception {
        //如果数据库连接不为空，关闭
        if (con != null) {
            con.close();
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        //获得数据库工具类对象
        DbUtil dbUtil = new DbUtil();
        try {
            //获得数据库链接
            dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


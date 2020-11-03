package com.su.dao;

import com.su.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户相关业务
 */
public class UserDao {
    /**
     * 登录验证
     * @param con 数据库连接
     * @param user 用户
     * @return
     * @throws Exception
     */
    public User login(Connection con, User user) throws Exception{
        //用户对象
        User resultUser=null;
        //查询
        String sql="select * from t_user where userName=? and password=?";
        //获取sql
        PreparedStatement pstmt=con.prepareStatement(sql);
        //获取实体属性
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}

package com.su.web;

import com.su.dao.UserDao;
import com.su.entity.User;
import com.su.util.DbUtil;
import com.su.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * 首页web层（与dao和页面交互）
 * @author Sujinran
 */
public class LoginServlet extends HttpServlet {
    //创建数据库对象dbUtil
    DbUtil dbUtil=new DbUtil();
    //创建业务对象userDao
    UserDao userDao=new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //从页面获取用户输入的userName
        String userName=request.getParameter("userName");
        System.out.println(userName);
        //请求用户输入的password
        String password=request.getParameter("password");
        System.out.println(password);
        //得到用户输入的用户名密码放到request
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        //判断用户输入是否为空
        if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
            //返回错误信息给request
            request.setAttribute("error", "用户名或密码为空！");
            //服务器跳转
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        //创建用户对象
        User user=new User(userName,password);
        System.out.println(user);
        //数据库连接对象
        Connection con=null;
        try {
            //获取链接
            con=dbUtil.getCon();
            //获取当前登录的用户
            User currentUser=userDao.login(con, user);
            System.out.println(currentUser);
            //如果当前用户为空
            if(currentUser==null){
                //页面显示错误
                request.setAttribute("error", "用户名或密码错误！");
                // 服务器跳转
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                // 获取Session
                HttpSession session=request.getSession();
                session.setAttribute("currentUser", currentUser);
                // 客户端跳转
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                //关闭数据库
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

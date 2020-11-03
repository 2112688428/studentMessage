package com.su.web;


import com.alibaba.fastjson.JSONObject;
import com.su.dao.StudentDao;
import com.su.util.DbUtil;
import com.su.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * 删除学生web层
 * @author Sujinran
 */
public class StudentDeleteServlet extends HttpServlet {
	//数据库对象
	DbUtil dbUtil=new DbUtil();
	//学生业务对象
	StudentDao studentDao=new StudentDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从页面获得编号
		String delIds=request.getParameter("delIds");
		System.out.println(delIds);
		//数据库连接对象
		Connection con=null;
		try{
			//获得链接
			con=dbUtil.getCon();
			//数据格式
			JSONObject result=new JSONObject();
			//删除
			int delNums=studentDao.studentDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
}

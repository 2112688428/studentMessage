package com.su.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.su.dao.StudentDao;
import com.su.entity.PageBean;
import com.su.entity.Student;
import com.su.util.DbUtil;
import com.su.util.JsonUtil;
import com.su.util.ResponseUtil;
import com.su.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * 学生主页
 * @author Sujinran
 */
public class StudentListServlet extends HttpServlet {
	DbUtil dbUtil=new DbUtil();
	StudentDao studentDao=new StudentDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//页面获得学生信息--服务器获得
		String stuNo=request.getParameter("stuNo");
		System.out.println(stuNo);
		String stuName=request.getParameter("stuName");
		System.out.println(stuName);
		String sex=request.getParameter("sex");
		System.out.println(sex);
		String bbirthday=request.getParameter("bbirthday");
		System.out.println(bbirthday);
		String ebirthday=request.getParameter("ebirthday");
		System.out.println(ebirthday);
		String gradeId=request.getParameter("gradeId");
		System.out.println(gradeId);
		//创建学生对象
		Student student=new Student();
		if(stuNo!=null){
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if(StringUtil.isNotEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));
			}
		}
		
		String page=request.getParameter("page");
		System.out.println(page);
		String rows=request.getParameter("rows");
		System.out.println(rows);
	
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray= JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean,student,bbirthday,ebirthday));
			int total=studentDao.studentCount(con,student,bbirthday,ebirthday);
			result.put("rows", jsonArray);
			result.put("total", total);
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

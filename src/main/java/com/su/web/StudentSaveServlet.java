package com.su.web;
import com.alibaba.fastjson.JSONObject;
import com.su.dao.StudentDao;
import com.su.entity.Student;
import com.su.util.DateUtil;
import com.su.util.DbUtil;
import com.su.util.ResponseUtil;
import com.su.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class StudentSaveServlet extends HttpServlet {
	//数据库工具对象
	DbUtil dbUtil=new DbUtil();
	//学生业务
	StudentDao studentDao=new StudentDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得页面学生信息
		request.setCharacterEncoding("utf-8");
		String stuNo=request.getParameter("stuNo");
		System.out.println(stuNo);
		String stuName=request.getParameter("stuName");
		System.out.println(stuName);
		String sex=request.getParameter("sex");
		System.out.println(sex);
		String birthday=request.getParameter("birthday");
		System.out.println(birthday);
		String gradeId=request.getParameter("gradeId");
		System.out.println(gradeId);
		String email=request.getParameter("email");
		System.out.println(email);
		String stuDesc=request.getParameter("stuDesc");
		System.out.println(stuDesc);
		String stuId=request.getParameter("stuId");
		System.out.println(stuId);
		
		Student student=null;
		try {
			student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
					Integer.parseInt(gradeId), email, stuDesc);
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if(StringUtil.isNotEmpty(stuId)){
			student.setStuId(Integer.parseInt(stuId));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(stuId)){
				saveNums=studentDao.studentModify(con, student);
			}else{
				saveNums=studentDao.studentAdd(con, student);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
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

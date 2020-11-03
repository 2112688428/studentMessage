package com.su.dao;

import com.su.entity.PageBean;
import com.su.entity.Student;
import com.su.util.DateUtil;
import com.su.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学生dao
 *
 * @author Sujinran
 */
public class StudentDao {
    /**
     * 模糊搜索
     * @param con 连接
     * @param pageBean 页面
     * @param student 学生
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    public ResultSet studentList(Connection con, PageBean pageBean, Student student, String bbirthday, String ebirthday)throws Exception{
        //联表查询
        StringBuffer sb=new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
        if(StringUtil.isNotEmpty(student.getStuNo())){
            sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getStuName())){
            sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getSex())){
            sb.append(" and s.sex ='"+student.getSex()+"'");
        }
        if(student.getGradeId()!=-1){
            sb.append(" and s.gradeId ='"+student.getGradeId()+"'");
        }
        if(StringUtil.isNotEmpty(bbirthday)){
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"')");
        }
        if(StringUtil.isNotEmpty(ebirthday)){
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * 查询学生
     * @param con 连接
     * @param student 学生
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    public int studentCount(Connection con,Student student,String bbirthday,String ebirthday)throws Exception{
        //联表查询
        StringBuffer sb=new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
        if(StringUtil.isNotEmpty(student.getStuNo())){
            sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getStuName())){
            sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getSex())){
            sb.append(" and s.sex ='"+student.getSex()+"'");
        }
        if(student.getGradeId()!=-1){
            sb.append(" and s.gradeId ='"+student.getGradeId()+"'");
        }
        if(StringUtil.isNotEmpty(bbirthday)){
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"')");
        }
        if(StringUtil.isNotEmpty(ebirthday)){
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

    /**
     * 删除学生
     * @param con 连接
     * @param delIds 删除编号
     * @return
     * @throws Exception
     */
    public int studentDelete(Connection con,String delIds)throws Exception{
        String sql="delete from t_student where stuId in("+delIds+")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /**
     * 增加学生
     * @param con 连接
     * @param student 学生
     * @return
     * @throws Exception
     */
    public int studentAdd(Connection con,Student student) throws Exception {
        String sql="INSERT INTO t_student (stuId,stuNo,stuName,sex,birthday,gradeId,email,stuDesc)VALUES(null,?,?,?,DATE_FORMAT(?,'%Y-%d-%m'),?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        return pstmt.executeUpdate();
    }

    /**
     * 更新学生信息
     * @param con 连接
     * @param student 学生
     * @return
     * @throws Exception
     */
    public int studentModify(Connection con,Student student)throws Exception{
        String sql="update t_student set stuNo=?,stuName=?,sex=?,birthday=DATE_FORMAT(?,'%Y-%d-%m'),gradeId=?,email=?,stuDesc=? where stuId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        pstmt.setInt(8, student.getStuId());
        return pstmt.executeUpdate();
    }

    /**
     * 通过班级查询学生
     * @param con 连接
     * @param gradeId 班级id
     * @return
     * @throws Exception
     */
    public boolean getStudentByGradeId(Connection con,String gradeId)throws Exception{
        String sql="select * from t_student where gradeId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, gradeId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }
}

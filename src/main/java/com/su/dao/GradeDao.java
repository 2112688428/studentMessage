package com.su.dao;


import com.su.entity.Grade;
import com.su.entity.PageBean;
import com.su.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 班级业务
 *
 * @author Sujinran
 */
public class GradeDao {
    /**
     * 根据班级名模糊搜索
     * @param con 连接
     * @param pageBean 页数
     * @param grade 班级
     * @return
     * @throws Exception
     */
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws Exception {
        //查询
        StringBuffer sb = new StringBuffer("select * from t_grade");
        if (grade != null && StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /**
     * 根据班级名模糊搜索
     * @param con 连接
     * @param grade 班级
     * @return
     * @throws Exception
     */
    public int gradeCount(Connection con, Grade grade) throws Exception {
        //查询
        StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    /**
     * 删除班级
     * @param con 连接
     * @param delIds 编号
     * @return
     * @throws Exception
     */
    public int gradeDelete(Connection con, String delIds) throws Exception {
        String sql = "delete from t_grade where id in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /**
     * 增加
     * @param con 连接
     * @param grade 班级
     * @return
     * @throws Exception
     */
    public int gradeAdd(Connection con, Grade grade) throws Exception {
        String sql = "insert into t_grade values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        return pstmt.executeUpdate();
    }

    /**
     * 更新班级
     * @param con 连接
     * @param grade 班级
     * @return
     * @throws Exception
     */
    public int gradeModify(Connection con, Grade grade) throws Exception {
        String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        pstmt.setInt(3, grade.getId());
        return pstmt.executeUpdate();
    }

}

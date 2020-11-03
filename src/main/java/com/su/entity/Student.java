package com.su.entity;

import java.util.Date;

/**
 * 学生实体类
 *
 * @author Sujinran
 */
public class Student {
    private int stuId;//编号
    private String stuNo;//学号
    private String stuName;//姓名
    private String sex;//性别
    private Date birthday;//生日
    private int gradeId=-1;//班级编号
    private String email;//邮箱
    private String stuDesc;//级别

    private String gradeName;//班级名

    public Student(String stuNo, String stuName, String sex, Date birthday, int gradeId, String email, String stuDesc) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.sex = sex;
        this.birthday = birthday;
        this.gradeId = gradeId;
        this.email = email;
        this.stuDesc = stuDesc;

    }

    public Student() {

    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStuDesc() {
        return stuDesc;
    }

    public void setStuDesc(String stuDesc) {
        this.stuDesc = stuDesc;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}

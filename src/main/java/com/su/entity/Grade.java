package com.su.entity;

/**
 * 班级实体类
 *
 * @author Sujinran
 */
public class Grade {

    private int id;//编号
    private String gradeName;//班级名
    private String gradeDesc;//班级描述


    public Grade() {
        super();
    }


    public Grade(String gradeName, String gradeDesc) {
        super();
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }


}

package com.jdbc1.exer;
/**
 * 练习用表 Student
 */
public class Student {
    
    private int flowID; // 流水号
    private int type; // 考试类型
    private String IDCard; // 身份证号
    private String examCard; // 准考证号
    private String name; // 学生姓名
    private String location; // 所在城市
    private int grade; // 成绩

    public Student() {
        super();
    }

    public Student(int flowID, int type, String IDCard, String examCard, String name, String location, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.IDCard = IDCard;
        this.examCard = examCard;
        this.name = name;
        this.location = location;
        this.grade = grade;
    }


    public int getFlowID() {
        return this.flowID;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIDCard() {
        return this.IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return this.examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        System.out.println("=====查询结果=====");
        return info();
    }

    private String info() {
        return "{" +
            " flowID='" + getFlowID() + "'" +
            ", type='" + getType() + "'" +
            ", IDCard='" + getIDCard() + "'" +
            ", examCard='" + getExamCard() + "'" +
            ", name='" + getName() + "'" +
            ", location='" + getLocation() + "'" +
            ", grade='" + getGrade() + "'" +
            "}";
    }

}

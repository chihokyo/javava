package com.design.princple;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则修改后
 */
public class DemeterB {
    public static void main(String[] args) {
        SchoolManagerB s = new SchoolManagerB();
        s.printAll(new CollegeManagerB());
    }
}


/**
 * 总部员工类
 */
class EmployeeB {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

/**
 * 学院员工类
 */

class CollegeEmployeeB {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**
 * 学院员工类-管理类
 */
class CollegeManagerB {

    // 新建10个学院员工
    public List<CollegeEmployeeB> getAllCollegeEmploye() {
        List<CollegeEmployeeB> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployeeB emp = new CollegeEmployeeB();
            emp.setId("CollegeEmployeeB id = " + i);
            list.add(emp);
        }
        return list;
    }
    // 打印输出
    public void printCollegeEmploye() {
        List<CollegeEmployeeB> list = getAllCollegeEmploye();
        System.out.println("-------CollegeEmployeeB-------");
        for (CollegeEmployeeB collegeEmployeeB : list) {
            System.out.println(collegeEmployeeB.getId());
        }
    }

}

/**
 * 总部员工类-管理类
 */
class SchoolManagerB {

     // 新建5个总部学院员工
    public List<EmployeeB> getAllEmploye() {
        List<EmployeeB> list = new ArrayList<EmployeeB>();
        for (int i = 0; i < 5; i++) {
            EmployeeB emp = new EmployeeB();
            emp.setId("EmployeeB id = " + i);
            list.add(emp);
        }
        return list;
    }

    /**
     * 获取上面2者全部 
     * 这里的 CollegeEmployee 不是 SchoolManager 的直接朋友
     * CollegeEmployee 是以局部变量方式出现在 SchoolManager
     * 违反了 迪米特法则
     */
    void printAll(CollegeManagerB sub) {
        // 分析问题 
        // 输出学院的员工方法 封装到 CollegeManagerB
        sub.printCollegeEmploye();

        List<EmployeeB> list2 = this.getAllEmploye();
        System.out.println("-------EmployeeB-------");
        for (EmployeeB employeeB : list2) {
            System.out.println(employeeB.getId());
        }
    }
}
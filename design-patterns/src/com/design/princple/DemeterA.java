package com.design.princple;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则修改前
 */
public class DemeterA {
    public static void main(String[] args) {
        SchoolManagerA s = new SchoolManagerA();
        s.printAll(new CollegeManagerA());
    }
}

/**
 * 总部员工类
 */
class EmployeeA {
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

class CollegeEmployeeA {
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
class CollegeManagerA {

    public List<CollegeEmployeeA> getAllEmploye() {
        List<CollegeEmployeeA> list = new ArrayList<CollegeEmployeeA>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployeeA emp = new CollegeEmployeeA();
            emp.setId("CollegeEmployee id = " + i);
            list.add(emp);
        }
        return list;
    }
}

/**
 * 总部员工类-管理类
 */
class SchoolManagerA {

    public List<EmployeeA> getAllEmploye() {
        List<EmployeeA> list = new ArrayList<EmployeeA>();
        for (int i = 0; i < 5; i++) {
            EmployeeA emp = new EmployeeA();
            emp.setId("EmployeeA id = " + i);
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
    void printAll(CollegeManagerA sub) {

        List<CollegeEmployeeA> list1 = sub.getAllEmploye();
        System.out.println("-------CollegeEmployeeA-------");
        for (CollegeEmployeeA collegeEmployeeA : list1) {
            System.out.println(collegeEmployeeA.getId());
        }

        List<EmployeeA> list2 = this.getAllEmploye();
        System.out.println("-------EmployeeA-------");
        for (EmployeeA employeeA : list2) {
            System.out.println(employeeA.getId());
        }
    }
}
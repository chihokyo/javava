package com.design.uml;
/**
 * 依赖关系
 * -----▷
 * 1) 类中用到了对方
 * 2) 如果是类的成员属性  PersonDaoDep personDaoDep;
 * 3) 如果是方法的返回类型 public IDCardDep
 * 4) 是方法接收的参数类型  save(PersonDep pd) {
 * 5) 方法中使用到 epartmentDep departmentDep 
 */
public class Dependence {
    
}

class PersonServiceBeanDep {
    private PersonDaoDep personDaoDep;
    public void save(PersonDep pd) {
        
    }
    public IDCardDep getIDCardDep(Integer personID) {
        return null;
    }

    public void modify() {
        DepartmentDep departmentDep = new DepartmentDep();
    }
}

class PersonDaoDep {}
class IDCardDep {}
class PersonDep {}
class DepartmentDep {}
package com.design.pattern.composite;

public class ClientMain {
    public static void main(String[] args) {

        // 从大到小创建学校-学院-专业
        OrganizationComponent  university = new University("Beijing University", "AAA");

        OrganizationComponent  colleague1 = new Colleague("IT colleague", "BBB");

        OrganizationComponent  colleague2 = new Colleague("Art colleague", "BBB");

        OrganizationComponent  department1 = new Department("Java department", "CCC");

        OrganizationComponent  department2 = new Department("PHP department", "CCC");

        OrganizationComponent  department3 = new Department("HTML department", "CCC");

         // 这里也可以直接添加
        colleague2.add(new Department("CSS", "CCC"));

        // colleague1.add(department1);
        // colleague1.add(department2);
        // colleague2.add(department3);
        // university.add(colleague1);
        // university.add(colleague2);

        university.add(colleague1);
        university.add(colleague2);
        colleague1.add(department1);
        colleague1.add(department2);
        colleague2.add(department3);
       

        university.print();
    

    }
}

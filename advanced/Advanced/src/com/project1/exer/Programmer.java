package com.project1.exer;
/**
 * 1级子类 Programmer 表示程序员职位
 *  继承于 Employee
 * 
 * @version
 * @author chin
 */

public class Programmer extends Employee {

    private int memberInt; // 员工工号
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {

    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        // 父类 public Employee(int id, String name, int age, double salary)
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberInt() {
        return this.memberInt;
    }

    public void setMemberInt(int memberInt) {
        this.memberInt = memberInt;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    protected String getMemberDetails() {
        return getMemberInt() + "/" + getDetails();
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\t程序员";
    }

    @Override
    public String toString() {
        return getDetails() + "\t程序员" + status + "\t\t\t" + equipment.getDescription();
    }

}

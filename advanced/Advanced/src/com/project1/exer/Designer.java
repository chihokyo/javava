package com.project1.exer;
/**
 * 2级子类 Designer 表示设计师职位
 *  继承于 Employee → Programmer
 */
public class Designer extends Programmer {

    private double bonus;

    public Designer() {

    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return this.bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String getDetailsForTeam() {
        return getMemberDetails() + "\t设计师\t" + getBonus();
    }

    @Override
    public String toString() {
        return getDetails() + "\t设计师\t" + getStatus() + "\t" + getBonus() + "\t\t" + getEquipment().getDescription();
    }

}

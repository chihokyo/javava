package com.objtest.exer;
/**
 * 关于 重写equals() == 的练习题2
 * date时间
 */
public class EqualsText3 {
    public static void main(String[] args) {
        Mydate m1 = new Mydate(13, 4, 2001);
        Mydate m2 = new Mydate(13, 4, 2001);
        if (m1 == m2) {
            System.out.println("m1 == m2");
        } else {
            System.out.println("m1 != m2");
        }

        if (m1.equals(m2)) {
            System.out.println("m1 equals m2");
        } else {
            System.out.println("m1 no m2");
        }
    }
}

class Mydate {

    private int day;
    private int month;
    private int year;

    public Mydate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Mydate other = (Mydate) obj;
        if (day != other.day) {
            return false;
        }
        if (month != other.month) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        return true;
    }
}

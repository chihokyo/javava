public class OOPexer {
    public static void main(String[] args) {
        /*************************************/
        PersonTest p1 = new PersonTest();
        p1.name = "Amy";
        p1.age = 10;
        p1.study();
        p1.showAge();

        int newAge = p1.addAge(1);
        System.out.println(p1.name + "的年龄是" + newAge);
        System.out.println(p1.age);
        p1.showAge();

        PersonTest p2 = new PersonTest();
        p2.showAge(); // 0 新对象 堆里面都是独立的，默认值
        p2.addAge(10);
        p2.showAge(); //  10

        /*************************************/
        Circle c1 = new Circle();
        c1.radius = 2.1;

        double newArea = c1.findArea();
        System.out.println(newArea);

        c1.findArea2();
    }
}


/**
 * 设计一个类
 */
class PersonTest{
    String name;
    int age;
    int sex;

    public void study() {
        System.out.println("studying");
    }

    public void showAge() {
        System.out.println(age);
    }

    public int addAge(int i) {
        age += i;
        return age;
    }
}

/**
 * 计算面积
 */
class Circle{

    double radius;

    public double findArea() {
        double area = Math.PI * radius * radius;
        return area;
    }

    public void findArea2() {
        double area2 = Math.PI * radius * radius;
        System.out.println(area2);
    }
}
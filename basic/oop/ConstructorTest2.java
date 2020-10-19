/**
 * 构造器练习
 * 编写2个类，
 */
public class ConstructorTest2 {
    public static void main(String[] args) {
        InnerConstructorTest2 i1 = new InnerConstructorTest2();
        i1.setBase(2.0);
        i1.setHeight(3.0);
        System.out.println("i1: " + i1.getBase() + " " + i1.getHeight());

        InnerConstructorTest2 i2 = new InnerConstructorTest2(3.7, 7.0);

        System.out.println("i2: " + i2.getBase() + " " + i2.getHeight());
    }
}


class InnerConstructorTest2 {

    private double base; 
    private double height;
    
    public double getBase() {
        return base;
    }
    
    public double getHeight() {
        return height;
    }

    public void setBase(double b) {
        base = b;
    }

    public void setHeight(double h) {
        height = h;
    }

    // 空参构造器
    public InnerConstructorTest2() {
        
    }

    // 构造器
    public InnerConstructorTest2(double b, double h) {
        base = b;
        height = h;
    }
}

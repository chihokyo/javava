/**
 * 封装问题
 * 1 创造一个对象，可以通过对象·属性 进行赋值，但是赋值操作要受到数据类型的限制。
 * 针对属性的封装，（或者是说隐藏）
 * 
 * 2 权限总共4个 private 缺省 protected public
 * 可以修饰 类和类的内部结构（属性，方法，构造器，内部类）
 * 3 修饰类只能使用 public 要么 缺省 
 * 不能有 private！！修饰类 因为类这个在外部类，没有所谓的内部概念。是要暴露给外面的。
 * 跨包 public
 * 跨类 缺省
 * 内部类 private
 * 
 */
public class Encapsulation {
    public static void main(String[] args) {

        InnerEncapsulation i1 = new InnerEncapsulation();
        i1.name = "yes";
        // i1.age = 18; The field InnerEncapsulation.age is not visible 存在但私有不可见
        i1.gender = false;
        i1.show();
        i1.setAge(188);
        i1.show();
        i1.setAge(200);
        // 写法一样
        // int test = i1.getAge();
        // System.out.println(test);
        System.out.println(i1.getAge());
    }
}


/**
 * InnerEncapsulation
 */
class InnerEncapsulation {

    String name;
    private int age;
    boolean gender;

    public void eat() {
        System.out.println("eat");
    }

    public void show() {
        System.out.println("name: " + name +  ",age: " + age + ",gender: " + gender);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int ag) {
        if (ag > 0) {
            age = ag;
        }
    }
}

/**
 * 总结 属性赋值的先后顺序
 * 
 * 1 默认初始化
 * 2 显示初始化
 * 3 构造器当中赋值
 * 4 通过实例化对象 对象.方法 or 对象.属性 来进行赋值
 * 
 * 以上操作的先后顺序
 * 1→2→3→4
 * 但是1，2，3，只是一次性的。4却可以多次赋值。
 */

public class SetValueProcess {
    public static void main(String[] args) {

        Process p = new Process();
        System.out.println(p); // Process@d716361
        System.out.println(p.name); // Process@null
        System.out.println(p.age); // 1

        // 下面就是测试赋值先后顺序的实验
        Process p1 = new Process(19);
        p1.setAge(100);
        System.out.println(p1.age); // 19

    }
}

class Process {

    String name;
    int age = 1;

    public Process() {
    }

    public Process(int a) {
        age = a;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAge(int a) {
        age = a;
    }
}

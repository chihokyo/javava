/**
 * 设计一个有泛型的方法
 * 为什么不是泛型类
 * 现在这个类具体使用的不是对象，而是这个类的方法。
 * 所以没必要去设计一个泛型类，使用泛型方法就可以。
 * 
 * 泛型只能接受类对象，不能是基本数据类型。所以int不行 Integer行
 * 
 * Java数据结构几乎所有标准库都是泛型类
 */
public class LinearSearch2 {

    private LinearSearch2() {
    };

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            // 这里注意点就是比较的是String，是一个对象。所以不能用==
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 由于泛型只能接受类对象。所以这不能是int 而是 Integer
        Integer[] data = { 24, 18, 12, 9, -6, 89 };
        // Java SE5开始就提供了自动装箱 输入的是int 8 实际上装箱之后就是 Integer 8
        int res = LinearSearch2.<Integer>search(data, 8);
        int res2 = LinearSearch2.<Integer>search(data, -6);
        System.out.println(res);
        System.out.println(res2);

        // 下面测试一个student类是否能查找到
        // 新建一个数组
        Student[] stus = {
            new Student("Alice"),
            new Student("Chin"),
            new Student("Bob"),
        };
        // 测试chin是否在stus数组里
        Student chin = new Student("Chin");

        int res3 = LinearSearch2.search(stus, chin);
        System.out.println(res3); // 1
        
        // 明明是在的，为什么-1 是因为 Student 没有重写 equals 
        // equals 父类Object默认的方法是对比的地址。那么肯定是找不到了。
        // 基本上包装类都会重写 equals ，自定义的类 就是使用的Object的 equals
        // 所以需要重写方法 忽视大小

        Student ali = new Student("alice");
        int res4 = LinearSearch2.search(stus, ali);
        System.out.println(res4); // 0
 
    }
}

/**
 * 测试用类Student
 */

class Student {

    private String name;
    public Student(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    @Override 
    public boolean equals(Object student) {
        // 当前类对象是否就是当前这个类，看是否是同一个对象
        // 同一个对象直接就true了不用比了，就一模一样了。
        if (this == student) {
            return true;
        }
        // 传入的对象是否为空
        if (student == null) {
            return false;
        }
        // 当前对象的类，和传入过来你要对比的类是否是一个类—
        if (this.getClass() != student.getClass()) {
            return false;
        }
        Student another = (Student)student;
        // return this.name.equals(another.name);
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }

}
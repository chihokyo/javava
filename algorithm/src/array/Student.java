package array;

/**
 * 测试类
 */
public class Student {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
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
        Student another = (Student) student;
        // return this.name.equals(another.name);
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)", name, score);
    }

}

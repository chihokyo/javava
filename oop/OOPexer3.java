/**
 * 一个学生的排序的案例
 */
public class OOPexer3 {
    public static void main(String[] args) {
        // Student s1 = new Student();
        // Student s1 = new Student();
        // Student s1 = new Student();

        // 新建一个 student类型数组
        Student[] stus = new Student[20];
        // 遍历数组进行初始化赋值
        for (int i = 0; i < stus.length; i++) {
            stus[i] = new Student();
            stus[i].id = (i + 1);
            // [1,6]
            stus[i].grade = (int) (Math.random() * (6 - 1 + 1) + 1);
            // [0,100]
            stus[i].score = (int) (Math.random() * (100 - 0 + 1) + 1);

        }

        // 这里很重要，就是对象并没有实例化
        OOPexer3 ob = new OOPexer3();

        // 输出所有对象
        ob.printStu(stus);

        System.out.println("**********华丽的分割线**********");

        // 输出年级为3的数据
        ob.searchStu(stus, 3);

        System.out.println("**********华丽的分割线**********");

        // 冒泡排序
        ob.sortStu(stus);
        // 重新输出所有对象
        ob.printStu(stus);

    }

    // 输出所有对象
    public void printStu(Student[] stus) {

        for (int i = 0; i < stus.length; i++) {
            System.out.println(stus[i].info());
        }
    }

    // 输出年级为3的数据
    public void searchStu(Student[] stus, int score) {
        for (int i = 0; i < stus.length; i++) {
            if (stus[i].grade == score) {
                System.out.println(stus[i].info());
            }
        }
    }

    // 冒泡排序
    public void sortStu(Student[] stus) {
        for (int i = 0; i < stus.length - 1; i++) {
            for (int j = 0; j < stus.length - 1 - i; j++) {
                if (stus[j].score >= stus[j + 1].score) {
                    // 如果需要交换顺序，那么交换的应该是对象，而不是score
                    Student temp = stus[j];
                    stus[j] = stus[j + 1];
                    stus[j + 1] = temp;
                }
            }
        }
    }

}

class Student {

    int id;
    int grade;
    int score;

    public String info() {
        return "学号:" + id + ",年级:" + grade + ",成绩:" + score;
    }

}
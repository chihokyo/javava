/**
 * 一个面向对象小练习
 * Boy and Girl
 * 
 */
public class ThisKeywordTest {
    public static void main(String[] args) {
        Boy boy = new Boy("Romoe", 23);
        boy.shout();

        Girl girl = new Girl("Juite", 20);
        girl.marry(boy);

        Girl girl2 = new Girl("祝英台", 20);
        int isOlder = girl.compare(girl2);

        if (isOlder > 0) {
            System.out.println(girl.getName() + "更大");

        } else if (isOlder < 0) {
            System.out.println(girl2.getName() + "更大");

        } else {
            System.out.println("一样大");

        }
    }
}

class Boy {

    private String name;
    private int age;

    public Boy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void marry(Girl girl) {
        System.out.println("I want " + girl.getName());
    }

    public void shout() {
        if (this.age >= 22) {
            System.out.println("Yes, U Can Love~");
        } else {
            System.out.println("No! U cant!");
        }
    }

}

class Girl {

    private String name;
    private int age;

    public Girl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void marry(Boy boy) {
        System.out.println("I want marry " + boy.getName());
        // 这里很重要，因为marry的是当前对象 this 这个时候不可以进行新建一个对象
        // boy.marry(new Girl()) NG
        boy.marry(this);
    }

    public int compare(Girl girl) {
        // if (this.age > girl.age){
        // return 1;
        // } else if(this.age < girl.age){
        // return -1;
        // } else {
        // return 0;
        // }
        // 以上这么多可以简化成
        return this.age - girl.age;
    }

}
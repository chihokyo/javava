/**
 * 匿名对象 
 * 1 创建对象，没有显式赋值给一个变量名 
 * 2 匿名对象只能调用一次，再次调用就是再次 new 的新空间 
 * 3 使用的时候 用工厂方法这样就不是匿名的，就是有同一个地址的
 */
public class Anonymous {
    public static void main(String[] args) {
        Phone p = new Phone();
        System.out.println("Phone的地址是:" + p); // Phone的地址是:Phone@2f0e140b

        p.sendMail(); // sendMail
        p.playGame(); // playGame

        // 匿名对象
        new Phone().sendMail();
        new Phone().playGame();

        // 匿名对象创建对象new就是一个新空间
        new Phone().price = 1999;
        new Phone().showPrice(); // 0.0

        /******************************************/
        // 如果使用的话就无需通过上面 Phone p = new Phone();这样的操作
        // 1. 新建一个 PhoneFactory 对象
        PhoneFactory factory = new PhoneFactory();
        // 2. 传入的必须是一个Phone对象，也就是另一个类实例化出来的
        factory.showPhone(new Phone());

    }
}

class PhoneFactory {
    // 到这里的时候，其实是把上面factory.showPhone(new Phone());的new Phone()地址当做形参给了下面
    // 下面的方法是同一个对象
    public void showPhone(Phone phone) {
        phone.sendMail();
        phone.playGame();
    }
}

class Phone {

    double price;

    public void sendMail() {
        System.out.println("sendMail");
    }

    public void playGame() {
        System.out.println("playGame");
    }

    public void showPrice() {
        System.out.println("price是 " + price);
    }

}
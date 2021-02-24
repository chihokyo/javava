/**
 * 一个小练习 银行 客户 余额存取功能
 */
public class ThisKeywordTest2 {
    public static void main(String[] args) {
        // 开始测试

        Bank bank = new Bank();
        bank.addCustomer("Amy", "OK");
        // 查询用户并进行设置账户初始化余额
        bank.getCustomer(0).setAccount(new Account(2000));
        bank.getCustomer(0).getAccount().withdraw(500);
        double balance = bank.getCustomer(0).getAccount().getBalance();
        String customer = bank.getCustomer(0).getFirstName();
        System.out.println(customer + " 的余额为: " + balance);

        System.out.println("**************************************");

        bank.addCustomer("Tome", "Tank");
        System.out.println("银行目前有顾客个数是: " + bank.getNumberOfCustomers());
    }
}

class Account {

    private double balance;

    public Account(double init_balance) {
        this.balance = init_balance;
    }

    public double getBalance() {
        return balance;
    }

    public void desposit(double amt) {
        if (amt > 0) {
            balance += amt;
            System.out.println("存钱成功");
        }
    }

    public void withdraw(double amt) {
        if (balance >= amt) {
            balance -= amt;
            System.out.println("取钱成功");
        } else {
            System.out.println("余额不足");
        }
    }

}

class Customer {

    private String firstName;
    private String lastName;
    private Account account;

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

class Bank {

    private Customer[] cusomers; // 存放客户数组
    private int numberOfCustomers; // 客户的数目

    public Bank() {
        // 先初始化客户，不然第一次调用会报错
        cusomers = new Customer[10];
    }

    // 添加客户
    public void addCustomer(String f, String l) {
        // 新建客户，增加信息，更新数组index 增加客户数目
        Customer cust = new Customer(f, l);
        // cusomers[numberOfCustomers] = cust;
        // numberOfCustomers++; 下面是简写 记住是后++
        // 为什么是后++ 先操作把0赋值给数组 在进行＋个数
        cusomers[numberOfCustomers++] = cust;
    }

    // 获取当前客户量
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    // 获取指定位置客户
    public Customer getCustomer(int index) {
        // 1 index 空指针问题 因为index可以越界
        // 2 可能不越界，但是本身没有客户，index也没
        if (index >= 0 && index < numberOfCustomers) {
            return cusomers[index];// 这样NG
        }

        return null;
    }
}

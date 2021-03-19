package com.design.pattern.bridge;

/**
 * Main执行类
 */
public class ClienMain {
    public static void main(String[] args) {
        Phone phone1 = new FoldedPhone(new Apple());
        phone1.open();
        phone1.close();
        phone1.call();
        // Apple Brand open
        // FoldedPhone
        // Apple Brand close
        // FoldedPhone
        // Apple Brand call
        // FoldedPhone

        Phone phone2 = new FoldedPhone(new XiaoMi());
        phone2.open();
        phone2.close();
        phone2.call();

        Phone phone3 = new StrightPhone(new Apple());
        phone3.open();
        phone3.close();
        phone3.call();

        Phone phone4 = new StrightPhone(new XiaoMi());
        phone4.open();
        phone4.close();
        phone4.call();
    }
}

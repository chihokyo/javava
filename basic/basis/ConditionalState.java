package basis;

public class ConditionalState {
    public static void main(String[] args) {
        //****************练习1
        System.out.println("---开始检查---");
        int heartBeats = 79;
        if (heartBeats < 60 || heartBeats > 100) {
            System.out.println("赶紧去检查吧。");
        }

        System.out.println("---检查结束---");

         //****************练习2
        int age = 23;
        if (age < 18) {
            System.out.println("18禁");      
        } else {
            System.out.println("成年人OK");
        }

        //****************练习3
        if (age < 0) {
            System.out.println("还没出生");
        } else if (age < 18) {
            System.out.println("还未成年");
        } else if (age < 50) {
            System.out.println("已经更年期");
        } else if (age < 100) {
            System.out.println("老年人");
        } else {
            System.out.println("修炼成仙");
        }
        //****************练习4
        double d1 = 2.13, d2 = 19.1;
        if (d1 > 10.0 && d2 < 20.0) {
            System.out.println(d1 + d2);
        } else {
            System.out.println(d1 - d2);
        }
        
    }
}

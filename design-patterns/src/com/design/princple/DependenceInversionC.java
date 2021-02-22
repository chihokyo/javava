package com.design.princple;

public class DependenceInversionC {
    public static void main(String[] args) {
        // // A 通过接口
        // OpenClose oc = new OpenClose();
        // oc.open(new Sony()); // Sony open

        // // B 通过构造方法
        // Sony s = new Sony();
        // OpenClose oc = new OpenClose(s);
        // oc.open(); // Sony open

        // C 通过setter方法传递
        OpenClose oc = new OpenClose();
        oc.setTV(new Sony());
        oc.open(); // Sony open


    }
}

// // A 通过接口
// interface SwitchOpenClose {
//     void open(TV tv);
// }

// interface TV {
//     void play();
// }

// // 实现接口
// class OpenClose implements SwitchOpenClose {
//     @Override
//     public void open(TV tv) {
//         tv.play();
//     }
// }

// SONY实现
class Sony implements TV {
    @Override
    public void play() {
        System.out.println("Sony open");
    }
}

// // B 通过构造方法
// interface SwitchOpenClose {
//     void open();
// }

// interface TV {
//     void play();
// }

// class OpenClose implements SwitchOpenClose {
//     public TV tv;
//     // 这里通过构造器，这里需要传入一个实现类
//     public OpenClose(TV tv) {
//         this.tv = tv;
//     }
//     @Override
//     public void open() {
//         this.tv.play();
//     }
// }

// C 通过setter方法传递
interface SwitchOpenClose {
    void open();
    // 抽象方法，这里必须要实现，就会通过set方法进行初始化了
    void setTV(TV tv);
}

interface TV {
    void play();
}

class OpenClose implements SwitchOpenClose {
    // 这里是私有的
    private TV tv;
    public void setTV(TV tv) {
        this.tv = tv;
    }
    @Override
    public void open() {
        this.tv.play();
    }
}
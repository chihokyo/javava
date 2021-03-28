package com.design.pattern.command;

/**
 * 命令模式
 * 使用一个遥控器 
 * 上面有开关和遥控全部的按钮
 */
public class ClientMain {
    public static void main(String[] args) {
        // ********使用遥控器操作 电灯**********
        // 创建电灯对象 （接受者）
        // 创建相关开关命令
        // 需要一个遥控器
        // 给遥控器发射指令

        LigntReciever ligntReciever = new LigntReciever();

        LigntOnCommand ligntOnCommand = new LigntOnCommand(ligntReciever);
        LigntOffCommand ligntOffCommand = new LigntOffCommand(ligntReciever);

        RemoteController remoteController = new RemoteController();
        //  这里默认 电灯是 0
        remoteController.setCommand(0, ligntOnCommand, ligntOffCommand);
        System.out.println("----电灯开----");
        remoteController.onButtonWasPushed(0);
        System.out.println("----电灯关----");
        remoteController.offButtonWasPushed(0);
        System.out.println("----撤销了电灯关的操作！----");
        remoteController.undoButtonWasPushed();

        TVReciever tvReciever = new TVReciever();

        TVOnCommand tvOnCommand = new TVOnCommand(tvReciever);
        TVOffCommand tvOffCommand = new TVOffCommand(tvReciever);
        //  这里默认 TV是 1
        remoteController.setCommand(1, tvOnCommand, tvOffCommand);
        System.out.println("----TV开----");
        remoteController.onButtonWasPushed(1);
        System.out.println("----TV关----");
        remoteController.offButtonWasPushed(1);
        System.out.println("----撤销了TV关的操作！----");
        remoteController.undoButtonWasPushed();



    }
}

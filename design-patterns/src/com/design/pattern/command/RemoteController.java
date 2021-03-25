package com.design.pattern.command;
/**
 * 遥控器 
 * 实现对所有家电的操作
 */
public class RemoteController {

    // 按钮的命令组 因为不只是控制1个家电
    Command[] onCommands;
    Command[] offCommands;
    Command unCommand;

    // 构造器 用来初始化
    public RemoteController() {
        // 这里默认生成5个家电的初始化按钮
        onCommands = new Command[5];
        offCommands = new Command[5];
        // 初始化默认设置成空按钮
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }
    // 给按钮设置需要的指令
    public void setCommand(int no, Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    // 开 按钮按下
    public void onButtonWasPushed(int no) {
        // 这里就直接执行 调用相应的方法
        onCommands[no].execute();
        // 这里记录下按钮 用来撤销
        unCommand = onCommands[no];
    }

    // 关 按钮按下
    public void offButtonWasPushed(int no) {
        // 同上
        offCommands[no].execute();
        unCommand = offCommands[no];
    }

    // 撤销操作
    public void undoButtonWasPushed() {
        // 因为这里默认实现了undo
        unCommand.undo();
    }

}

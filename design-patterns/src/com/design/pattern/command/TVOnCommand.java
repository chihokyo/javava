package com.design.pattern.command;

public class TVOnCommand implements Command {

    TVReciever tv;

    public TVOnCommand(TVReciever tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        // 调用接收者的方法
        // 这里的执行 实际上要看逻辑 需要执行什么命令
        tv.on();
        
    }

    @Override
    public void undo() {
        // 同上
        tv.off();
    }
}

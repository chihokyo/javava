package com.design.pattern.command;

public class TVOffCommand implements Command {

    TVReciever tv;

    public TVOffCommand(TVReciever tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        // 调用接收者的方法
        // 这里的执行 实际上要看逻辑 需要执行什么命令
        tv.off();
        
    }

    @Override
    public void undo() {
        // 同上
        tv.on();
    }
}

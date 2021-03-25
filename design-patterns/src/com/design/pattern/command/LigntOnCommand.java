package com.design.pattern.command;

/**
 * LigntOnCommand 开的命令
 * 实现了 Command
 */
public class LigntOnCommand  implements Command {

    // 聚合 LigntReciever
    LigntReciever light;

    // 构造器用来初始化一个接收器实例

    public LigntOnCommand(LigntReciever light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 调用接收者的方法
        // 这里的执行 实际上要看逻辑 需要执行什么命令
        light.on();
        
    }

    @Override
    public void undo() {
        // 同上
        light.off();
    }
    
}

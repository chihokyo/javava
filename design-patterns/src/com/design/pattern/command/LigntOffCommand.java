package com.design.pattern.command;
/**
 * LigntOffCommand 关上的命令
 */
public class LigntOffCommand  implements Command {

    // 聚合 LigntReciever
    LigntReciever light;

    // 构造器用来初始化一个接收器实例

    public LigntOffCommand(LigntReciever light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 调用接收者的方法
        // 这里的执行 实际上要看逻辑 需要执行什么命令
        // 这里要注意 不同于开关的开 实际上执行的是off
        light.off();
        
    }

    @Override
    public void undo() {
        // 同上 要和开按键相反才行
        light.on();
    }
    
}

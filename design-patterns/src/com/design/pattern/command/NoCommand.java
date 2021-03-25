package com.design.pattern.command;

/**
 * 空指令
 * 默认什么都不实现 只是初始化每个按钮
 * 当调用空命令的时候 对象什么都不做
 * 这样也是一种设计模式 可以省略空判断
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        
    }

    @Override
    public void undo() {
        
    }
    
}

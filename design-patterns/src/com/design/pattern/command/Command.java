package com.design.pattern.command;
/**
 * 创建命令接口
 */
public interface Command {
    // 执行
    public void execute();
    // 撤销
    public void undo();
}

package com.design.uml;
/**
 * 关联关系
 * 具有导航性质（双向，or 单向）
 * →
 */
public class Association {
    
}

/**
 * 单向1对1
 */
class PersonAssoA {
    private IDCardAsso card;
}

class IDCardAsso {}

/**
 * 双向1对1 
 */
class PersonAssoB {
    private IDCardAssoB card;
}

class IDCardAssoB {
    private PersonAssoB p;
}
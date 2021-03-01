package com.design.uml;

/**
 * 泛化关系
 * ー▷
 * 有继承就是一种泛化关系
 * 1) 泛化关系实际上就是继承关系
 * 2) 如果A类继承了B类，我们就说A和B存在泛化关系
 */
public class Generalization {
    
}

abstract class DaoSupportGne {
    public void sava(Object entity){

    }
    public void delete(Object id) {
        
    }
}

class PersonServiceBeanGne extends DaoSupportGne {

}
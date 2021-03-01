package com.design.uml;
/**
 * 实现关系
 * 
 * 就是接口实现而已
 * ・・・・▷
 */
public class Implementation {
    
}


interface PersonServiceImpl {
    public void delete(Integer id);
}

class PersonServiceBeanImpl implements PersonServiceImpl {
    @Override
    public void delete(Integer id) {
        
    }
}
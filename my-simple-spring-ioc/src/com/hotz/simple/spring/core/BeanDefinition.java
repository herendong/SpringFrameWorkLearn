package com.hotz.simple.spring.core;

/**
 * @ClassName BeanDefinition
 * @Description TODO
 * @Author herendong
 * @Date 2019/10/26 11:03
 **/
public class BeanDefinition {

    private String flag;

    private Class<? extends Object> clazz;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Object> getObjectClass() {
        return clazz;
    }

    public void setObjectClass(Class<? extends Object> objectClass) {
        this.clazz = objectClass;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

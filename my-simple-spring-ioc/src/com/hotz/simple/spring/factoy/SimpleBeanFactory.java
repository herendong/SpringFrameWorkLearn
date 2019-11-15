package com.hotz.simple.spring.factoy;

import com.hotz.simple.spring.core.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SimpleBeanFactory
 * @Description  工厂本身就是容器, 可以保存对象,beanDefiniiton等, 创建对象
 * @Author herendong
 * @Date 2019/10/26 11:00
 **/
public abstract class SimpleBeanFactory {

    //TODO:基础容器存放beanDefinition 并发map, 这里为什么要用线程安全的map
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存放bean名字
    private volatile List<String> beanDefinitionNames = new ArrayList<>();
    //存放bean
    private volatile Map<String,Object> singletonBeanCache = new ConcurrentHashMap<>();

    /**
     * bean定义
     */
    public Object getBean(String name) throws IllegalAccessException, InstantiationException {
        if(this.singletonBeanCache.containsKey(name))
            return singletonBeanCache.get(name);
        if(!beanDefinitionNames.contains(name))
            return null;

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        Class<?> objectClass = beanDefinition.getObjectClass();
        return objectClass.newInstance();
    }

    public void setBeanDefinitionNames(String beanDefinitionNames) {
       this.beanDefinitionNames.add(beanDefinitionNames);
    }


    public void setBeanDefinitionMap(String name,BeanDefinition beanDefinition ){
        if(this.beanDefinitionMap.containsKey("name")){
            System.out.println("Bean 已经加载");
            return;
        }
        this.beanDefinitionMap.put(name,beanDefinition);
    }

}

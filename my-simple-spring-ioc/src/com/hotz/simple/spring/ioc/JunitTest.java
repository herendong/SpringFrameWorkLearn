package com.hotz.simple.spring.ioc;

import com.hotz.simple.spring.factoy.SpecialBeanFactory;

/**
 * @ClassName JunitTest
 * @Description TODO
 * @Author herendong
 * @Date 2019/10/26 14:55
 **/
public class JunitTest {

    public static void main(String[] args) throws Throwable {
        SpecialBeanFactory specialBeanFactory = new SpecialBeanFactory(new String[]{"E:\\shanghaiyunye\\my-simple-spring-ioc\\fileconfig\\test.txt","fileconfig/testtwo.txt"});
        specialBeanFactory.getBean("simplebean");
        specialBeanFactory.getBean("testTwoBean");
    }
}

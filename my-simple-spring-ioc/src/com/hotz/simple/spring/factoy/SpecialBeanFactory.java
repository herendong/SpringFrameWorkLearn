package com.hotz.simple.spring.factoy;

import com.hotz.simple.spring.core.BeanDefinition;
import com.sun.deploy.util.StringUtils;
import com.sun.xml.internal.ws.util.StreamUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @ClassName SpecialBeanFactory
 * @Description 针对不同文件可能读取文件类型不同以及一些特有的处理方法
 * @Author herendong
 * @Date 2019/10/26 14:03
 **/
public class SpecialBeanFactory extends SimpleBeanFactory {

    /**
     * 读取文件并解析和定义
     */
    public SpecialBeanFactory(String... str) throws Throwable{
        super();
        int length = str.length;
        if(length<1){
            System.out.println("文件加载失败");
            return;
        }
        for (int i = 0; i <length ; i++) {
            String split = str[i].substring(str[i].lastIndexOf(".")+1);
            switch(split){
                case "xml":
                    break;
                case "properties":
                    break;
                case "txt":
                    txtFileProcess(str[i]);
                    break;
                default:
                    System.out.println("文件类型不匹配");
                    break;
            }
        }
    }

    private void txtFileProcess(String s) throws Exception {
        File file = new File(s);
        if(!file.isFile()){
            return;
        }
        BufferedReader bufferedReader =null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String str = null;
            BeanDefinition bd = new BeanDefinition();
            while (true) {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                //将文件写入BeanDefinition
                writeBeanDefinition(str, bd);
                if (bd.getFlag() == "end") {
                    //注册beanDefinition
                    this.setBeanDefinitionMap(bd.getName(), bd);
                    this.setBeanDefinitionNames(bd.getName());
                    bd = new BeanDefinition();//防止被修改
                    bd.setFlag(null);//恢复标记, 标记使用以后一定要恢复
                }
            }
        }catch (Exception e){
            System.out.println("异常信息,"+e);
        }finally {
            //资源回收
            bufferedReader.close();
        }
    }

    private void writeBeanDefinition(String str,BeanDefinition bd) throws ClassNotFoundException {
        if(str.contains("clazz")){
            String[] split = str.split("=");
            Class<?> aClass = Class.forName(split[1]);
            bd.setObjectClass(aClass);
        }
        if(str.contains("name")){
            String[] split = str.split("=");
            bd.setName(split[1]);
        }
        if(str.contains("end")){
            bd.setFlag("end");
        }
    }
}

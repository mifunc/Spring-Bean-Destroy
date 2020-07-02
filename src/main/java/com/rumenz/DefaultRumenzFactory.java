package com.rumenz;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultRumenzFactory implements  InitializingBean, DisposableBean {

    public DefaultRumenzFactory() {
        System.out.println("无参构造方法执行....");
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct init.......");
    }
    public void initMethod(){
        System.out.println("init method.......");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet.....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy 执行中。。。");
    }

    public void destroy1(){
        System.out.println("@Bean(destroyMethod = \"destroy1\") 自定义销毁方法执行...");
    }
}

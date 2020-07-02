package com.rumenz;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PreDestroy;

public class DemoApplication {
    public static  void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register(DemoApplication.class);
        ac.refresh();
        System.out.println("Spring 上下文启动完成。。。。。");
        final DefaultRumenzFactory bean = ac.getBean(DefaultRumenzFactory.class);

        System.out.println("Spring 上下文准备关闭。。。。。");
        ac.close();
        System.out.println("Spring 上下文已关闭。。。。。");
    }
    @Bean(initMethod = "initMethod",destroyMethod = "destroy1")
    public static  DefaultRumenzFactory defaultRumenzFactory(){
        return new DefaultRumenzFactory();
    }

    @PreDestroy
    public void destroy(){
        System.out.println("PreDestroy执行。。。。");
    }





}

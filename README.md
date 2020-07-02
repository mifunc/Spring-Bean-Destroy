- @PreDestroy Java标准注解
- 实现DisposableBean的Destroy()方法
- 自定义销毁方法
  - XML 配置:<bean destroy="destroy" ... />
  - Java注解 @Bean(destroy="destroy")
  - Java API AbstractBeanDefinition#setDestroyMethodName(String)


**DemoApplication.java**

```java
package com.rumenz;
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

```

**DefaultRumenzFactory.java**

```java
package com.rumenz;
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

```

**输出**

```
Spring 上下文准备关闭。。。。。
DisposableBean#destroy 执行中。。。
@Bean(destroyMethod = "destroy1") 自定义销毁方法执行...
PreDestroy执行。。。。
Spring 上下文已关闭。。。。。
```

> AnnotationConfigApplicationContext.close() 触发了销毁操作

源码:https://github.com/mifunc/Spring-Bean-Destroy

原文: [https://rumenz.com/rumenbiji/Spring-Bean-Destroy.html](https://rumenz.com/rumenbiji/Spring-Bean-Destroy.html)

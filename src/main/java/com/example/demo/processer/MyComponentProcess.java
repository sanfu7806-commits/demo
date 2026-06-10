package com.example.demo.processer;

import com.example.demo.annotation.MyComponent;
import com.example.demo.handler.SimpleInvocationHandler;

public class MyComponentProcess {
    /**
     * 1.扫描指定包下面所有的类
     * 2.按照反射实例化bean
     * 3.按照类型自动注入依赖
     * 4.支持aop
     */
    //增强目标类
    public static Object process(Object o){
        SimpleInvocationHandler simpleInvocationHandler = new SimpleInvocationHandler(o);
        Object proxy = simpleInvocationHandler.getProxy();
        Class<?> proxyClass = proxy.getClass();
        //判断是否是MyComponment标识
        if(!proxyClass.isAnnotationPresent(MyComponent.class)){
            return 0;
        }else{return proxyClass;}
    }
}
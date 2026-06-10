package com.example.demo.handler;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibInvocationHandler implements MethodInterceptor {
    private Object target;

    public CglibInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB 方法执行前: " + method.getName());
        Object result = methodProxy.invoke(target, args);
        System.out.println("CGLIB 方法执行后: " + method.getName());
        return result;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        // CGLIB通过继承目标类来生成代理，不需要接口
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
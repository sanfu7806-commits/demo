package com.example.demo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleInvocationHandler implements InvocationHandler {
    private Object target;

    public SimpleInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前 - 织入逻辑");
        Object result = method.invoke(target, args);
        System.out.println("方法执行后 - 织入逻辑");
        return result;
    }

    // 生成代理对象
    public Object getProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
}
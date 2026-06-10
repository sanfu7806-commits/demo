package com.example.demo.processer;

import com.example.demo.annotation.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogProcess {
    public static void process(Object object) throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if(declaredMethod.isAnnotationPresent(Log.class)){
                Log log = declaredMethod.getAnnotation(Log.class);
                System.out.println("调用方法"+declaredMethod.getName()+"日志信息"+log.value());
                declaredMethod.invoke(object);
            }
        }
    }
}
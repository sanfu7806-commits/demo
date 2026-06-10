package com.example.demo.processer;

import com.example.demo.annotation.Add;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AddProcess {
    public static int process(Object object) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Add.class)) {
                Field[] fields = object.getClass().getDeclaredFields();
                int sum = 0;
                for (Field field : fields) {
                    if (field.getType() == int.class || field.getType() == Integer.class) {
                        try {
                            field.setAccessible(true);
                            Object value = field.get(object);
                            if (value != null) {
                                sum += (Integer) value;
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println(sum);
            }
        }
        return 1;
    }
}

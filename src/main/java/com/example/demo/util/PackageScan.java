package com.example.demo.util;

import com.example.demo.annotation.MyAutowried;
import com.example.demo.entity.BeanDefination;
import com.example.demo.entity.Boy;
import com.example.demo.tools.MyTools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PackageScan {
    /**
     * @param pack
     * @return BeanDefinations
     */
    public Set<BeanDefination> packageScan(String pack){
        /**
         * 1.处理包名MyTools，并返回Set<Class<?>>,得到BeanDefination的clazzName属性
         * 2.获得beanName
         * 3.返回beandefinations
         */
        Set<Class<?>> classes = MyTools.getClasses(pack);
        Set<BeanDefination> beanDefinations = new LinkedHashSet<>();
        for (Class<?> aClass : classes) {
            String beanName = lowerFirstChar(aClass.getSimpleName());
            BeanDefination beanDefination = new BeanDefination(aClass, beanName);
            beanDefinations.add(beanDefination);
        }
        return beanDefinations;
    }

    public String lowerFirstChar(String str){
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    public static Map<String,Object> creatBean(Set<BeanDefination> beanDefinations){
        Map<String,Object> ioc = new ConcurrentHashMap<>();
        Iterator<BeanDefination> iterator = beanDefinations.iterator();
        //全部创建bean，并且存入ioc容器中
        while (iterator.hasNext()) {
            BeanDefination beanDefination = iterator.next();
            Class clazz = beanDefination.getClazzName();
            String beanName = beanDefination.getBeanName();
            try {
                //实例化
                Object clazzConstructor = clazz.getConstructor().newInstance();
                ioc.put(beanName,clazzConstructor);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        //处理MyAutoWried
        for (BeanDefination beanDefination : beanDefinations) {
            Object object = ioc.get(beanDefination.getBeanName());
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(MyAutowried.class)){
                    MyAutowried myAutowried = declaredField.getAnnotation(MyAutowried.class);
                    String value = myAutowried.value();
                    //将value赋值给object
                    declaredField.setAccessible(true);
                    //创建method也就是set方法
                    Method method = null;
                    try {
                        String methodName =
                                "set"+declaredField.getName().substring(0,1).toUpperCase()+
                                        declaredField.getName().substring(1);
                        method = object.getClass().getMethod(methodName,declaredField.getType());
                        Object val = null;
                        switch (declaredField.getType().getName()){
                            case "java.lang.Integer":
                                val = Integer.parseInt(value);
                                break;
                            case "java.lang.String":
                                val = value;
                                break;
                            case "java.lang.Long":
                                val = Long.parseLong(value);
                                break;
                            case "java.lang.Double":
                                val = Double.parseDouble(value);
                                break;
                            case "java.lang.Fo":
                                val = Float.parseFloat(value);
                                break;
                            default:
                                continue;
                        }
                        method.invoke(object,val);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return ioc;
    }

}
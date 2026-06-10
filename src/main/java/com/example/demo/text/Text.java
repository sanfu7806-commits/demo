package com.example.demo.text;

import com.example.demo.entity.BeanDefination;
import com.example.demo.entity.Boy;
import com.example.demo.entity.Student;
import com.example.demo.handler.CglibInvocationHandler;
import com.example.demo.handler.SimpleInvocationHandler;
import com.example.demo.util.PackageScan;


import java.util.Map;
import java.util.Set;

public class Text {
    public static void main(String[] args) {
        PackageScan packageScan = new PackageScan();
        Set<BeanDefination> beanDefinations = packageScan.packageScan("com.example.demo.entity");
        Map<String, Object> ioc = PackageScan.creatBean(beanDefinations);

        // 从容器里拿出 Boy 实例
        Boy boy = (Boy) ioc.get("boy");
        CglibInvocationHandler cglibInvocationHandler = new CglibInvocationHandler(boy);
        Boy proxy = (Boy)cglibInvocationHandler.getProxy();
        System.out.println("name:"+proxy.getName());
        Student student = (Student)ioc.get("student");
        System.out.println(student.getName());
        System.out.println(student.getName());
    }
}
package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.processer.AddProcess;
import com.example.demo.processer.LogProcess;
import com.example.demo.processer.SqlProcess;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;

@Service
public class EntityService {
    //这块本质上动态代理了
    @Autowired
    TestService testService;

    @PostConstruct
    public void useTest() throws InvocationTargetException, IllegalAccessException {
        Student student = new Student("li","32","b");
        LogProcess.process(testService);
        AddProcess.process(testService);
        String sql = SqlProcess.process(student);
        System.out.println(sql);
    }

}

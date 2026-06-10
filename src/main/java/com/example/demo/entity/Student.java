package com.example.demo.entity;

import com.example.demo.annotation.InsertSql;
import com.example.demo.annotation.MyAutowried;
import com.example.demo.annotation.MyComponent;
import com.example.demo.annotation.SqlColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@InsertSql(tableName = "student")
@MyComponent
@Data
public class Student {
    @SqlColumn(column = "name")
    @MyAutowried(value = "sufan")
    String name;
    @SqlColumn(column = "age")
    String age;
    @SqlColumn(column = "gender")
    String gender;

    public Student(String name ,String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student() {
    }
}
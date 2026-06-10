package com.example.demo.entity;

import com.example.demo.annotation.MyAutowried;
import com.example.demo.annotation.MyComponent;
import org.springframework.stereotype.Component;

@MyComponent

public class Boy {
    @MyAutowried(value = "01")
    String name;

    public Boy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boy() {
    }
}

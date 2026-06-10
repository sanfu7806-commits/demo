package com.example.demo.service;

import com.example.demo.annotation.Add;
import com.example.demo.annotation.Log;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    int i = 1;
    int j = 2;
    Integer K = 15;
    String m = "7";
    @Log("11")
    public int test(){
        return -1;
    }

    @Add
    public int test1(){
        return -1;
    }
}
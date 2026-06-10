package com.example.demo.entity;

public class BeanDefination {
    public Class clazzName;
    public String beanName;

    public BeanDefination(Class clazzName, String beanName) {
        this.clazzName = clazzName;
        this.beanName = beanName;
    }

    public Class getClazzName() {
        return clazzName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setClazzName(Class clazzName) {
        this.clazzName = clazzName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public BeanDefination() {
    }
}

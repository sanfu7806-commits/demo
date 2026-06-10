package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    //id
    Long id;
    //name
    String name;
    //age
    Integer age;
    //性别
    String gender;
    //部门
    String department;
    //职位
    String position;
    //薪资
    BigDecimal salary;
    //手机号
    String phone;
    //邮箱
    String email;
    //是否离职
    Boolean status;
    //创建时间
    LocalDateTime createTime;
    //更新时间
    LocalDateTime updateTime;
}

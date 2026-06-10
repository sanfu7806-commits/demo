package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;

import com.example.demo.vo.EmployeeSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImple extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEmployee(Employee employee) {
        return baseMapper.insert(employee) > 0;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateById(Employee employee) {

        if (baseMapper.updateById(employee) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public EmployeeSearch searchEmployeeByParam(String param) {
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Employee::getName,param)
                .or().like(Employee::getAge,param)
                .or().like(Employee::getEmail,param)
                .or().like(Employee::getPhone,param)
                .or().like(Employee::getGender, param)
                .or().like(Employee::getDepartment, param)
                .or().like(Employee::getPosition, param);
        List<Employee> employees = baseMapper.selectList(lambdaQueryWrapper);
        EmployeeSearch employeeSearch = new EmployeeSearch();
        employeeSearch.setEmployees(employees);
        employeeSearch.setParam(param);
        return employeeSearch;
    }
}
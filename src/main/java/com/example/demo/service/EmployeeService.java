package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Employee;
import com.example.demo.vo.EmployeeSearch;

public interface EmployeeService extends IService<Employee> {
    boolean saveEmployee(Employee employee);

    Employee findEmployeeById(Long id);

    boolean deleteEmployee(Long id);

    boolean updateById(Employee employee);

    EmployeeSearch searchEmployeeByParam(String param);
}

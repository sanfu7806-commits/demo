package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.vo.EmployeeSearch;
import com.example.demo.vo.MyResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public MyResultBody<Employee> saveEmployee(@RequestBody Employee employee) {
        boolean saved = employeeService.saveEmployee(employee);
        if (saved) {
            return MyResultBody.success(200, "新增成功", employee);
        }
        return MyResultBody.error(500, "新增失败");
    }

    /**
     * 删除
     */
    @DeleteMapping("/deleteEmployee/{id}")
    public MyResultBody<Employee> deleteEmployee(@PathVariable long id){
        boolean b = employeeService.deleteEmployee(id);
        if (b){
            return MyResultBody.success(200,"删除成功");
        }
        return MyResultBody.error(500,"删除失败");
    }

    /**
     * 查询
     */
    @GetMapping("/findByEmployee/{id}")
    public MyResultBody<Employee> findEmployee(@PathVariable long id){
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null){
            return MyResultBody.success(200,"查询成功",employee);
        }
        return MyResultBody.error(404,"员工不存在");
    }

    /**
     * 修改
     */
    @PutMapping("updateById")
    public MyResultBody<Employee> updateById(@RequestBody Employee employee){
        if(employee.getId() == null){
            return MyResultBody.error(400,"id不能为空");
        }
        if (employeeService.updateById(employee)) {
            return MyResultBody.success(200,"更新成功");
        }
        return MyResultBody.error(400,"更新失败");
    }

    /**
     * 模糊查询，给任意一个参数，模糊查询，并把查询到的数据传回来，且将查到的数据，连同查询的参数也传回来
     */
    @GetMapping("/search")
    public MyResultBody<EmployeeSearch>searchEmployee(@RequestParam String param){
        if (param == null||param.trim().isEmpty()){
            return MyResultBody.error(400,"参数不能为空");
        }
        EmployeeSearch employeeSearch = employeeService.searchEmployeeByParam(param);
        return MyResultBody.success(200,"查询成功",employeeSearch);
    }

}

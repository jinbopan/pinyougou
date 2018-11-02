package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("findByid/{id}")
    public Employee findbyid(@PathVariable("id") Integer id){
        return employeeService.search(id);
    }
}

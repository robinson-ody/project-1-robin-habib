package com.future.service;

import com.future.model.Employee;
import com.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("EmployeeService")
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll (){
        return employeeRepository.findAll();
    }


}

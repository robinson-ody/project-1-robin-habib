/*
package com.future.services;

import com.future.exception.ResourceNotFoundException;
import com.future.model.Employee;
import com.future.repository.EmployeeRepository;


public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Employee findUserByUsernameAndPassword(String username, String password) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findByUsernameAndPasswordAndEnabled(username,password,true);
        if(employee==null){
            throw new ResourceNotFoundException("Employee","username/password",employee+ "/"+password);
        }
        System.out.println(employee);
        return employee;
    }
}
*/

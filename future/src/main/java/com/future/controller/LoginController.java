/*
package com.future.controller;

import com.future.model.Employee;
import com.future.model.Login;
import com.future.model.Users;
import com.future.repository.UsersRepository;
import com.future.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/login")
    public Employee login(@Valid
                      @RequestBody Login loginModel) {
        return employeeService.findUserByUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword());

    }

}
*/

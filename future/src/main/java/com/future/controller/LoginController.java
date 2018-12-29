package com.future.controller;

import com.future.model.Employee;
import com.future.model.LoginRequest;
import com.future.model.LoginResponse;
import com.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    EmployeeRepository employeeRepository;

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        LoginResponse a = new LoginResponse();
        Employee employeeDataUsername = employeeRepository.findByUsername(request.getUsername());
        if(employeeDataUsername==null){
            a.setSuccess(false);
            return a;
        }
        if (request.getUsername().equals(employeeDataUsername.getUsername()) && request.getPassword().equals(employeeDataUsername.getPassword())) {
            if(employeeDataUsername.getRole().equals("ADMIN")){
                a.setSuccess(true);
                a.setName(employeeDataUsername.getName());
                a.setRole(employeeDataUsername.getRole());
                return a;}
            else if(employeeDataUsername.getRole().equals("MANAGER")){
                a.setSuccess(true);
                a.setName(employeeDataUsername.getName());
                a.setRole(employeeDataUsername.getRole());
                return a;
            }
            else {
                a.setSuccess(false);
            }
        }
        else{
            a.setSuccess(false);
        }
        a.setSuccess(false);
        return a;
    }
}

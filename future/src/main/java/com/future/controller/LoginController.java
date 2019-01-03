package com.future.controller;

import com.future.model.Employee;
import com.future.model.requestResponse.LoginRequest;
import com.future.model.requestResponse.LoginResponse;
import com.future.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    EmployeeRepository employeeRepository;

    @CrossOrigin
    @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        LoginResponse a = new LoginResponse();
        Employee employeeData = employeeRepository.findByEmail(request.getEmail());
        if(employeeData==null){
            a.setSuccess(false);
            return a;
        }
        if (request.getEmail().equals(employeeData.getEmail()) && request.getPassword().equals(employeeData.getPassword())) {
            if(employeeData.getRole().equals("ADMIN")){
                a.setSuccess(true);
                a.setName(employeeData.getName());
                a.setRole(employeeData.getRole());
                return a;}
            else if(employeeData.getRole().equals("MANAGER")){
                a.setSuccess(true);
                a.setName(employeeData.getName());
                a.setRole(employeeData.getRole());
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

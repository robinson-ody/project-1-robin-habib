package com.future.controller;

import com.future.model.Employee;
import com.future.model.requestResponse.LoginRequest;
import com.future.model.requestResponse.LoginResponse;
import com.future.repository.EmployeeRepository;
import com.future.service.EmployeeService;
import com.future.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    LoginService loginService;

    @CrossOrigin
    @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        return loginService.authenticate(request); }
}

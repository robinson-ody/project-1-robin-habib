package com.future.controller;

import com.future.model.LoginRequest;
import com.future.model.LoginResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticate(@RequestBody LoginRequest request) {
        LoginResponse a = new LoginResponse();
        // this logic should be moved inside service implementation
        // this is just an example: should get from repo the status based on username
        if (request.getUsername().equals("1") && request.getPassword().equals("1")) {
            a.setStatus("admin");
            a.setSuccess(true);
            return a;
        } else if (request.getUsername().equals("2") && request.getPassword().equals("2")) {
            a.setStatus("employee");
            a.setSuccess(true);
        }
        a.setSuccess(false);
        return a;
    }
}
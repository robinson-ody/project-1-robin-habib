package com.controller;

import com.repository.LoginRepository;
import com.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller //untuk membuat data yang mau dikirim ke http diconvert menjadi JSON
@RequestMapping("/auth")
public class LoginController {
    private LoginRepository loginRepository;
    @Autowired public LoginController(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String HomePage() {
        return "index";
    }


    @RequestMapping(value="/actionLogin", method=RequestMethod.GET)
    public String login(@PathVariable("role") String role, String username,String password, Model model) {
        List<Login> usernameLogin = loginRepository.findByUsername(username);
        List<Login> passwordLogin = loginRepository.findByPassword(password);
        if (usernameLogin != null) {
            model.addAttribute("username", usernameLogin);
        }
        if (passwordLogin != null) {
            model.addAttribute("password", passwordLogin);
        }
        return "actionLogin";
    }



}
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

@Controller

public class LoginController {

    private LoginRepository loginRepository;
    @Autowired public LoginController(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    //UNTUK KONEKSI KE HTML DENGAN LOCALHOST:8080/auth/login
    @RequestMapping(value="/auth/login", method=RequestMethod.GET)//GET DI SEBELAH INI MASIH SALAH. HARUS MENGGUNAKAN POST DAN MEMAHAMI SWAGGER
    public String loginPage() {
        return "loginPage";
    }



    /*MASIH ERROR KODE DIBAWAH INI BELUM BISA ACTION UNTUK MENGECEK DATA YG DIINPUT DENGAN DATA DI  DATABASE*/
    @RequestMapping(value="auth/actionLogin", method=RequestMethod.POST)
    public String login(@PathVariable("role") String role, String username,String password, Model model) {
        List<Login> usernameLogin = loginRepository.findByUsername(username);
        List<Login> passwordLogin = loginRepository.findByPassword(password);
        if (usernameLogin != null && passwordLogin!=null) {
            return "actionLogin";
        }
        return "loginPage";


    }


}
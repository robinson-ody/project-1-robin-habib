/*
package com.controller;

import com.repository.LoginRepository;
import com.model.Login;
import io.swagger.models.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController {
*/
/*
    private LoginRepository loginRepository;
    public LoginController(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
*//*


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("username", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Login login) {
        return "home";
    }


*/
/*
    @GetMapping("/login")
    public String HomePage() {
        return "login";
    }
*//*


*/
/*    @RequestMapping(value="/actionLogin", method=RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
        @ResponseBody
        public String login(String json, Model model) {
            JSONObject jsonObject = JSONObject.wrap();
                String username = jsonObject.get("username").toString();
                String password = jsonObject.get("password").toString();

                List<Login> usernameLogin = loginRepository.findByUsername(username);
                List<Login> passwordLogin = loginRepository.findByPassword(password);
                if (usernameLogin != null) {
                    model.addAttribute("username", usernameLogin);
                }
                if (passwordLogin != null) {
                    model.addAttribute("password", passwordLogin);
                }

                if(username == usernameLogin && password == passwordLogin){
                    return "home";
                } else {
                    return "login";
                    console.write("Please Input the Correct Username and Password");
                }*//*


*/
/*            String usernameLogin = loginRepository.findByUsername(parameterMap.get("username").toString()).toString();
            String passwordLogin = loginRepository.findByPassword(parameterMap.get("password").toString()).toString();
            if (usernameLogin != null) {
                System.out.println("Username Found");
                if (passwordLogin != null){
                    System.out.println("Password Found");
                    return "home";
                }
                return "login";
            } else {
                System.out.println("Please Input the Correct Username and Password");
                return "login";
        }*//*


*/
/*            String userLogin = formData.get("username").toString();
            String passLogin = formData.get("password").toString();


        if(Objects.equals(usernameLogin.toString(), userLogin) && Objects.equals(passwordLogin.toString(), passLogin)){
                return "home";
            } else {
                System.out.println("Please Input the Correct Username and Password");
                return "login";
            }*//*

    }*/

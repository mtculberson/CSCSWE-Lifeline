package com.example.demo.User;

import javax.validation.Valid;

import com.example.demo.User.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration
@RestController()

public class UserController{

    //@GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }
}
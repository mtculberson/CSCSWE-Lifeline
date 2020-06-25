package com.example.demo;

import javax.validation.Valid;

import com.example.demo.User.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController()

public class UserController{

    @GetMapping(value="/sign-up", name="sign-up")
    public @ResponseBody String getSignUp() {
        return "sign-up";
    }
}
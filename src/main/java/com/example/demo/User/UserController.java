package com.example.demo.User;

import javax.validation.Valid;

import com.example.demo.User.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller()

public class UserController{

    @RequestMapping("/sign-up")
    String getSignUp() {
        return "sign-up";
    }

    @RequestMapping("/sign-in")
    String getSignIn() {
        return "sign-in";
    }

    @RequestMapping("/forgot")
    String getForgot() {
        return "forgot";
    }
}
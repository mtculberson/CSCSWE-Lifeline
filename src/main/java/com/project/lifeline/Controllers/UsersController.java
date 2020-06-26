package com.project.lifeline.Controllers;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        model.addAttribute("user", new RegisterUserModel());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String postSignUp(@Valid @ModelAttribute RegisterUserModel user,BindingResult results){
        if(results.hasErrors()){
            return "sign-up";
        }
        this.usersService.createNewUser(user);
        return "index";
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
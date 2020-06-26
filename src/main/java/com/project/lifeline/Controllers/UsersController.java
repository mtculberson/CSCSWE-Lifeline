package com.project.lifeline.Controllers;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.SignInUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView postSignUp(@Valid @ModelAttribute RegisterUserModel user,BindingResult results, ModelAndView modelAndView){
        if(results.hasErrors()){
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }
        this.usersService.createNewUser(user);

        modelAndView.addObject("FirstName", user.getFirstName());
        modelAndView.addObject("LastName", user.getLastName());
        modelAndView.addObject("PhoneNumber", user.getPhoneNumber());
        modelAndView.addObject("Email", user.getEmail());
        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @RequestMapping("/sign-in")
    String getSignIn() {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public ModelAndView postSignIn(@Valid @ModelAttribute SignInUserModel user, BindingResult results, ModelAndView modelAndView){
        if(results.hasErrors()){
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }
        this.usersService.signIn(user);

        modelAndView.addObject("Email", user.getEmail());
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logOut(BindingResult results, ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/forgot")
    String getForgot() {
        return "forgot";
    }
}
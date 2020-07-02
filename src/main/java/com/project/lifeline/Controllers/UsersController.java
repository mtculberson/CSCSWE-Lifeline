package com.project.lifeline.Controllers;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.SignInUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        modelAndView.addObject("Username", user.getUsername());
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @GetMapping("/login")
    public String getSignIn(Model model) {
        model.addAttribute("user", new SignInUserModel());
        return "login";
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

    @GetMapping("/dashboard")
    String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/addcontact")
    String getAddContact(){
        return "addcontact";
    }

    @GetMapping("/deletecontact")
    String getDeleteContact(){
        return "deletecontact";
    }
    @GetMapping("/updatecontact")
    String getUpdateContact(){
        return "updatecontact";
    }

}
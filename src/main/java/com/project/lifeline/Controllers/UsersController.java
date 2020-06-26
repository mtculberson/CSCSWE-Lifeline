package com.project.lifeline.Controllers;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/sign-up")
    String getSignUp() {
        //usersService.createNewUser(new RegisterUserModel());
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
package com.project.lifeline.Controllers;

import com.project.lifeline.Services.UserServiceImpl;
import com.project.lifeline.Models.RegisterUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController{

    @RequestMapping("/sign-up")
    String getSignUp() {
        UserServiceImpl service = new UserServiceImpl();
        service.createNewUser(new RegisterUserModel()); //should go in POST
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
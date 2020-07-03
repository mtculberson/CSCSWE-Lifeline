package com.project.lifeline.Controllers;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import org.hibernate.Query;
import org.springframework.security.core.Authentication;
import com.project.lifeline.Models.SignInUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        model.addAttribute("registerUserModel", new RegisterUserModel());
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView postSignUp(@Valid @ModelAttribute("registerUserModel") RegisterUserModel registerUserModel,BindingResult results, ModelAndView modelAndView){
        List<Users> users = usersService.findAll();
        for (int i = 0; i < users.size(); i++){
            if (registerUserModel.getUsername().equals(users.get(i).getUsername())){
                results.addError(new FieldError("registerUserModel", "Username", registerUserModel.getUsername(), false, null, null, "Email already exists in database."));
            }
        }

        if (!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword()))
            results.addError(new FieldError("registerUserModel", "ConfirmPassword", registerUserModel.getPassword(), false, null, null, "Passwords do not match."));

        if(results.hasErrors()){
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }

        this.usersService.createNewUser(registerUserModel);

        modelAndView.addObject("FirstName", registerUserModel.getFirstName());
        modelAndView.addObject("LastName", registerUserModel.getLastName());
        modelAndView.addObject("PhoneNumber", registerUserModel.getPhoneNumber());
        modelAndView.addObject("Username", registerUserModel.getUsername());
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
    String getDashboard(Authentication user) {
        String username = user.getName();
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
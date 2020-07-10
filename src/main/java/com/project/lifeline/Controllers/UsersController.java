package com.project.lifeline.Controllers;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import com.project.lifeline.Services.ContactService;
import com.project.lifeline.Services.EmergencyContactService;
import org.hibernate.Query;
import org.springframework.security.core.Authentication;
import com.project.lifeline.Models.SignInUserModel;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        model.addAttribute("registerUserModel", new RegisterUserModel());
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView postSignUp(@Valid @ModelAttribute("registerUserModel") RegisterUserModel registerUserModel,BindingResult results, ModelAndView modelAndView){
        Users user = usersService.findUserByUsername(registerUserModel.getUsername());
        if (user != null)
            results.addError(new FieldError("registerUserModel", "Username", registerUserModel.getUsername(), false, null, null, "Email already exists in database."));


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

    @GetMapping("/dashboard")
    String getDashboard(Authentication user) {
        String username = user.getName();
        return "dashboard";
    }

    @GetMapping("/accountsettings")
    public String getAccountSettings(Model model, Authentication user) {
        String username = user.getName();
        Users userInfo = usersService.findUserByUsername(username);
        Contact contactInfo = contactService.getContactById(userInfo.getContactId());

        RegisterUserModel account = new RegisterUserModel();
        account.setFirstName(contactInfo.getFirstName());
        account.setLastName(contactInfo.getLastName());
        account.setUserId(userInfo.getUserId());
        account.setContactId(userInfo.getContactId());
        account.setPhoneNumber(contactInfo.getPhoneNumber());
        account.setUsername(userInfo.getUsername());
        account.setCreatedOn(contactInfo.getCreatedOn());

        model.addAttribute("account", account);

        return "accountsettings";
    }

    @RequestMapping(value = "/accountsettings", method = RequestMethod.POST)
    public ModelAndView postAccountSettings(@Valid @ModelAttribute("account") RegisterUserModel registerUserModel, BindingResult results, ModelAndView modelAndView, Authentication auth) {
        if (!registerUserModel.getUsername().equals(auth.getName()))
            results.addError(new FieldError("registerUserModel", "Username", registerUserModel.getUsername(), false, null, null, "You cannot change your Email."));

        if (!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword()))
            results.addError(new FieldError("account", "ConfirmPassword", registerUserModel.getPassword(), false, null, null, "Passwords do not match."));

        if(results.hasErrors()){
            modelAndView.setViewName("accountsettings");
            return modelAndView;
        }

        results.addError(new FieldError("registerUserModel", "UserId", registerUserModel.getUserId(), false, null, null, "Your account has been updated."));

        this.usersService.updateUser(registerUserModel);

        modelAndView.setViewName("accountsettings");
        modelAndView.addObject("account", registerUserModel);
        return modelAndView;
    }

    @GetMapping(value = "/confirmdelete")
    public String getConfirmDelete(Model model, Authentication auth) {
        String username = auth.getName();
        Users userInfo = usersService.findUserByUsername(username);
        Contact contactInfo = contactService.getContactById(userInfo.getContactId());

        RegisterUserModel account = new RegisterUserModel();
        account.setFirstName(contactInfo.getFirstName());
        account.setLastName(contactInfo.getLastName());
        account.setUserId(userInfo.getUserId());
        account.setContactId(userInfo.getContactId());
        account.setPhoneNumber(contactInfo.getPhoneNumber());
        account.setUsername(userInfo.getUsername());
        account.setCreatedOn(contactInfo.getCreatedOn());

        model.addAttribute("account", account);

        return "confirmdelete";
    }

    @GetMapping(value = "/deleteaccount")
    public String getDeleteAccount(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        new SecurityContextLogoutHandler().logout(request, response, auth);

        Users user = this.usersService.findUserByUsername(auth.getName());
        Contact contact = this.contactService.getContactById(user.getContactId());
        this.emergencyContactService.deleteEmergencyContacts(user.getUserId());
        this.usersService.deleteUser(user);
        this.contactService.deleteContactsForUser(contact);

        return "login";
    }

}
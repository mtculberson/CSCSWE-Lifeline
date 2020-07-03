package com.project.lifeline.Controllers;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Services.ContactService;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/emergencycontact")
    String getEmergencyContact(Model model, Authentication auth) {
        List<Contact> contacts = contactService.findEmergencyContacts(auth);
        model.addAttribute("contacts", contacts);
        return "emergencycontact";
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

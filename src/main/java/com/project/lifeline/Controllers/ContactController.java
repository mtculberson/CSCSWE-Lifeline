package com.project.lifeline.Controllers;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Services.ContactService;
import com.project.lifeline.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    String getAddContact(Model model){
        model.addAttribute("contact", new Contact());
        return "addcontact";
    }

    @PostMapping("/addcontact")
    String postAddContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult results, Model model, Authentication auth){
        if(results.hasErrors()){
            return "addcontact";
        }

        contactService.createNewContact(contact, auth);
        List<Contact> contacts = contactService.findEmergencyContacts(auth);
        model.addAttribute("contacts", contacts);
        return "emergencycontact";
    }

    @GetMapping("/deletecontact")
    String getDeleteContact(String id, Model model){
        UUID contactId = UUID.fromString(id);
        Contact contact = contactService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "deletecontact";
    }

    @PostMapping("/deletecontact")
    String postDeleteContact(@Valid @ModelAttribute("contact") Contact contact, Model model, Authentication auth){
        contactService.deleteContact(contact);

        List<Contact> contacts = contactService.findEmergencyContacts(auth);
        model.addAttribute("contacts", contacts);
        return "emergencycontact";
    }

    @GetMapping("/updatecontact")
    String getUpdateContact(String id, Model model){
        UUID contactId = UUID.fromString(id);
        Contact contact = contactService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "updatecontact";
    }

    @PostMapping("/updatecontact")
    String postUpdateContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult results, Model model, Authentication auth) {
        if(results.hasErrors()){
            return "addcontact";
        }

        contactService.updateContact(contact);
        List<Contact> contacts = contactService.findEmergencyContacts(auth);
        model.addAttribute("contacts", contacts);
        return "emergencycontact";
    }

    @GetMapping("/video")
    String getVideo(){
        return "video";
    }

    @GetMapping("/sendvideo")
    String getSendVideo(){
        return "sendvideo";
    }
}

package com.project.lifeline.Controllers;

import com.project.lifeline.Models.*;
import com.project.lifeline.Services.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
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

}

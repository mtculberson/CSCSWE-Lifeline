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

    @Autowired
    private VideoService videoService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private SMSService smsService;

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

    @GetMapping("/videoload")
    String getVideo(Model model){
        model.addAttribute("videoSaveModel", new VideoSaveModel());
        return "videoload";
    }

    @PostMapping("/videoload")
    String postVideo(@RequestParam("file") MultipartFile file, Authentication auth){
        Users user = usersService.findUserByUsername(auth.getName());

        Video videoModel = videoService.addVideo(file);
        videoService.addEmergency(user.getUserId(), videoModel.getVideoId());
        return "redirect:/sendvideo?id=" + videoModel.getVideoId().toString();
    }

    @GetMapping("/sendvideo")
    String getSendVideo(String id, Model model, Authentication auth){
        VideoSendModel videoSendModel = new VideoSendModel();
        List<Contact> contacts = contactService.findEmergencyContacts(auth);

        videoSendModel.setVideoId(UUID.fromString(id));
        videoSendModel.setContacts(contacts);
        model.addAttribute("videoSendModel", videoSendModel);
        return "sendvideo";
    }

    @PostMapping("/sendvideo")
    ModelAndView postSendVideo(@Valid @ModelAttribute("videoSendModel") VideoSendModel videoSendModel,BindingResult results, ModelAndView modelAndView) throws IOException {
        videoSendModel.setSelectedContact(contactService.getContactById(videoSendModel.getContactId()));

        String contactName = videoSendModel.getSelectedContact().getFirstName() + " " + videoSendModel.getSelectedContact().getLastName();
        String message = videoSendModel.getMessage();
        String phone = videoSendModel.getSelectedContact().getPhoneNumber();

        String path = "http://lifeline-env.eba-pegzmaqe.us-east-2.elasticbeanstalk.com/watchvideo?id=" + videoSendModel.getVideoId();
        message += " Click here to view emergency video: " + path;

        try {
            smsService.sendSMS(phone, message);
        } catch (Exception e) {
            modelAndView.setViewName("errorText");
            return modelAndView;
        }

        modelAndView.addObject("ContactName", contactName);
        modelAndView.addObject("Message", message);
        modelAndView.addObject("PhoneNumber", phone);
        modelAndView.setViewName("successText");
        return modelAndView;
    }

    @GetMapping("/watchvideo")
    @ResponseBody public ResponseEntity<byte[]> getWatchVideo(String id) throws IOException {
        Video video = videoService.getVideById(UUID.fromString(id));

        File outputFile = null;
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(video.getVideoData()));
        String mimeType = URLConnection.guessContentTypeFromStream(is);

        try {
            outputFile = File.createTempFile("file", "." + mimeType);
            outputFile.deleteOnExit();
            FileOutputStream fileoutputstream = new FileOutputStream(outputFile);
            fileoutputstream.write(video.getVideoData());
            fileoutputstream.close();
        } catch (IOException ex) {
            return null;
        }

        return null;

    }

}

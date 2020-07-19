package com.project.lifeline.Controllers;

import com.project.lifeline.Models.*;
import com.project.lifeline.Services.ContactService;
import com.project.lifeline.Services.SMSService;
import com.project.lifeline.Services.UsersService;
import com.project.lifeline.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class VideoController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private SMSService smsService;

    @GetMapping("/videoupload")
    String getVideo(Model model){
        model.addAttribute("videoSaveModel", new VideoSaveModel());
        return "videoupload";
    }

    @PostMapping("/videoupload")
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
    ModelAndView postSendVideo(@Valid @ModelAttribute("videoSendModel") VideoSendModel videoSendModel, BindingResult results, ModelAndView modelAndView) throws IOException {
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
    public ResponseEntity<byte[]> getWatchVideo(String id) throws IOException {
        Optional<Video> video = videoService.findById(UUID.fromString(id));
        
        File outputFile = null;
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(video.get().getVideoData()));
        String mimeType = URLConnection.guessContentTypeFromStream(is);

        try {
            outputFile = File.createTempFile("file", "." + mimeType);
            outputFile.deleteOnExit();
            FileOutputStream fileoutputstream = new FileOutputStream(outputFile);
            fileoutputstream.write(video.get().getVideoData());
            fileoutputstream.close();
        } catch (IOException ex) {
            return null;
        }

        return null;

    }

}

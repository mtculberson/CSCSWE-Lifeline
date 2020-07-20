package com.project.lifeline.Controllers;

import com.google.common.io.Resources;
import com.google.common.primitives.Longs;
import com.project.lifeline.Models.*;
import com.project.lifeline.Services.ContactService;
import com.project.lifeline.Services.SMSService;
import com.project.lifeline.Services.UsersService;
import com.project.lifeline.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ModelAndView getWatchVideo(String id, ModelAndView modelAndView) {
        modelAndView.setViewName("watchvideo");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @GetMapping("/watchvideo2")
    @ResponseBody
    public final ResponseEntity<InputStreamResource> watchVideo(@RequestHeader(value = "Range", defaultValue = "bytes=0-%s")
                                                                            String range, String id) throws Exception {
        Optional<Video> video = videoService.findById(UUID.fromString(id));

        byte[] videoBytes = video.get().getVideoData();
        long contentLength = videoBytes.length;

        if(range.equals("bytes=0-")) {
            range = range + contentLength;
        } else if (range.equals("bytes=0-%s")) {
            range = String.format(range, contentLength);
        } else if(range.equals("bytes=0-1")) { //safari
            range = range.replace("1", contentLength + "");
        }
        else if (range == null) {
            range = "bytes=0-" + contentLength;
        }

        long rangeStart = Longs.tryParse(range.replace("bytes=","").split("-")[0]);//parse range header, which is bytes=0-10000 or something like that
        long rangeEnd = Longs.tryParse(range.replace("bytes=","").split("-")[1]);

        InputStream inputStream = new ByteArrayInputStream(videoBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("video/" + video.get().getMimeType()));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Expires", "0");
        headers.set("Cache-Control", "no-cache, no-store");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Transfer-Encoding", "binary");
        headers.set("Content-Length", contentLength + "");

        //if start range assume that all content
        if (rangeStart == 0) {
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
        } else {
            headers.set("Content-Range", String.format("bytes %s-%s/%s", rangeStart, rangeEnd, contentLength));
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.PARTIAL_CONTENT);
        }
    }
}

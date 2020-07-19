package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.Emergency;
import com.project.lifeline.Models.Users;
import com.project.lifeline.Models.Video;
import com.project.lifeline.Repositories.EmergencyRepository;
import com.project.lifeline.Repositories.VideoRepository;
import com.sun.xml.bind.api.impl.NameConverter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService implements IVideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EmergencyRepository emergencyRepository;

    public List<Video> findAll() {

        Iterable<Video> it = videoRepository.findAll();

        ArrayList<Video> videos = new ArrayList<Video>();
        it.forEach(e -> videos.add(e));

        return videos;
    }


    public Video addVideo(MultipartFile videoData){
        Video video = new Video();

        byte[] bytes = null;

        try {
            bytes = videoData.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);

        } catch (IOException e) {
            return null;
        }

        String extension = FilenameUtils.getExtension(videoData.getOriginalFilename());

        video.setMimeType(extension);
        video.setVideoData(bytes);
        video.setCreatedOn(LocalDateTime.now());

        Video vid = videoRepository.save(video);

        return vid;
    }

    public void addEmergency(UUID UserId, UUID VideoId){
        Emergency emergency = new Emergency();
        emergency.setUserId(UserId);
        emergency.setVideoId(VideoId);
        emergency.setCreatedOn(LocalDateTime.now());

        emergencyRepository.save(emergency);

    }

    @Override
    public Optional<Video> findById(UUID id) {
          return videoRepository.findById(id);
    }
}

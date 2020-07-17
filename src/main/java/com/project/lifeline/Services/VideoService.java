package com.project.lifeline.Services;

import com.project.lifeline.Models.Emergency;
import com.project.lifeline.Models.Video;
import com.project.lifeline.Repositories.EmergencyRepository;
import com.project.lifeline.Repositories.VideoRepository;
import com.sun.xml.bind.api.impl.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    VideoRepository videoRepository;

    @Autowired
    EmergencyRepository emergencyRepository;

    public Video addVideo(String videoData){
        Video video = new Video();
        video.setVideoData(videoData.getBytes(StandardCharsets.UTF_16));
        video.setCreatedOn(LocalDateTime.now());

        videoRepository.save(video);

        return video;
    }

    public void addEmergency(UUID UserId, UUID VideoId){
        Emergency emergency = new Emergency();
        emergency.setUserId(UserId);
        emergency.setVideoId(VideoId);
        emergency.setCreatedOn(LocalDateTime.now());

        emergencyRepository.save(emergency);

    }
}

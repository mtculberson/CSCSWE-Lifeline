package com.project.lifeline.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="video", schema = "dbo")
public class Video {

    public UUID getVideoId() {
        return VideoId;
    }

    public void setVideoId(UUID videoId) {
        VideoId = videoId;
    }

    public byte[] getVideoData() {
        return VideoData;
    }

    public void setVideoData(byte[] videoData) {
        VideoData = videoData;
    }

    @Id
    @Column(name = "VIDEO_ID" , columnDefinition="uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID VideoId;

    @Column(name = "VIDEO_DATA" , columnDefinition="varbinary(max)")
    private byte[] VideoData;

    public LocalDateTime getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        CreatedOn = createdOn;
    }

    @Column(name = "CREATED_ON" , columnDefinition="datetime")
    private LocalDateTime CreatedOn;
}

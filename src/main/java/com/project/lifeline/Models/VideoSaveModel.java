package com.project.lifeline.Models;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class VideoSaveModel {
    public UUID getVideoId() {
        return VideoId;
    }

    public void setVideoId(UUID videoId) {
        VideoId = videoId;
    }

    public String getVideoDataStr() {
        return VideoDataStr;
    }

    public void setVideoDataStr(String videoDataStr) {
        VideoDataStr = videoDataStr;
    }

    private UUID VideoId;

    @NotNull
    private String VideoDataStr;


}

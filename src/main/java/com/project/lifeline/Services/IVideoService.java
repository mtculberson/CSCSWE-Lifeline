package com.project.lifeline.Services;

import com.project.lifeline.Models.Video;

import java.util.Optional;
import java.util.UUID;

public interface IVideoService {
    Optional<Video> findById(UUID id);
}

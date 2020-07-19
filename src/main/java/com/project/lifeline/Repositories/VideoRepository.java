package com.project.lifeline.Repositories;

import com.project.lifeline.Models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends CrudRepository<Video, UUID> {
    Optional<Video> findById(UUID id);
}

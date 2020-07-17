package com.project.lifeline.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="emergency", schema = "dbo")
public class Emergency {

    @Id
    @Column(name = "EMERGENCY_ID" , columnDefinition="uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID EmergencyId;

    @Column(name = "USER_ID" , columnDefinition="uniqueidentifier")
    private UUID UserId;

    @Column(name = "VIDEO_ID" , columnDefinition="uniqueidentifier")
    private UUID VideoId;

    @Column(name = "LOCATION_ID" , columnDefinition="uniqueidentifier")
    private UUID LocationId;

    public LocalDateTime getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        CreatedOn = createdOn;
    }

    @Column(name = "CREATED_ON" , columnDefinition="datetime")
    private LocalDateTime CreatedOn;


    public UUID getEmergencyId() {
        return EmergencyId;
    }

    public void setEmergencyId(UUID emergencyId) {
        EmergencyId = emergencyId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public UUID getVideoId() {
        return VideoId;
    }

    public void setVideoId(UUID videoId) {
        VideoId = videoId;
    }

    public UUID getLocationId() {
        return LocationId;
    }

    public void setLocationId(UUID locationId) {
        LocationId = locationId;
    }

}

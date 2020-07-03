package com.project.lifeline.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="emergency_contact", schema = "dbo")
public class EmergencyContact {

    @Id
    @Column(name = "EMERGENCY_CONTACT_ID" , columnDefinition="uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID EmergencyContactId;

    @Column(name = "USER_ID" , columnDefinition="uniqueidentifier")
    private UUID UserId;

    @Column(name = "CONTACT_ID" , columnDefinition="uniqueidentifier")
    private UUID ContactId;


    public UUID getEmergencyContactId() {
        return EmergencyContactId;
    }

    public void setEmergencyContactId(UUID emergencyContactId) {
        EmergencyContactId = emergencyContactId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public UUID getContactId() {
        return ContactId;
    }

    public void setContactId(UUID contactId) {
        ContactId = contactId;
    }

}

package com.project.lifeline.Models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="contact", schema = "dbo")
public class Contact {

    @Id
    @Column(name = "CONTACT_ID" , columnDefinition="uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ContactId;

    @Column(name = "FIRST_NAME" , columnDefinition="nvarchar(255)")
    private String FirstName;

    @Column(name = "LAST_NAME", columnDefinition="nvarchar(255)")
    private String LastName;

    @Column(name = "PHONE_NUMBER" , columnDefinition="nvarchar(20)")
    private String PhoneNumber;

    @CreatedDate
    @Column(name = "CREATED_ON" , columnDefinition="datetime")
    private LocalDateTime CreatedOn;

    @LastModifiedDate
    @Column(name = "UPDATED_ON" , columnDefinition="datetime")
    private LocalDateTime UpdatedOn;

    public UUID getContactId() {
        return ContactId;
    }

    public void setContactId(UUID contactId) {
        ContactId = contactId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        CreatedOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        UpdatedOn = updatedOn;
    }
}

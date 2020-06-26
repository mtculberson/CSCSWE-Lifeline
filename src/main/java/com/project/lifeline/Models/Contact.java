package com.project.lifeline.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="contact", schema = "dbo")
public class Contact {

    @Id
    @Column(name = "CONTACT_ID" , columnDefinition="uniqueidentifier")
    private UUID ContactId;

    @Column(name = "FIRST_NAME" , columnDefinition="nvarchar(255)")
    private String FirstName;

    @Column(name = "LAST_NAME", columnDefinition="nvarchar(255)")
    private String LastName;

    @Column(name = "PHONE_NUMBER" , columnDefinition="nvarchar(20)")
    private String PhoneNumber;

    @Column(name = "CREATED_ON" , columnDefinition="datetime")
    private Date CreatedOn;

    @Column(name = "UPDATED_ON" , columnDefinition="datetime")
    private Date UpdatedOn;

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

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }

    public Date getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        UpdatedOn = updatedOn;
    }
}

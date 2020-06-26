package com.project.lifeline.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "User")
public class User {

    @Id
    private UUID UserId;
    private String Email;
    private String Password;
    private UUID ContactId;

    public User() {

    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UUID getContactId() {
        return ContactId;
    }

    public void setContactId(UUID contactId) {
        ContactId = contactId;
    }
}

package com.project.lifeline.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users", schema = "dbo")
public class Users {

    @Id
    @Column(name = "USER_ID" , columnDefinition="uniqueidentifier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID UserId;


    @Column(name = "EMAIL" , columnDefinition="nvarchar")
    private String Email;

    @Column(name = "PASSWORD" , columnDefinition="varbinary")
    private byte[] Password;

    @Column(name = "ROLE", columnDefinition = "nvarchar(255)")
    private String Role;

    @Column(name = "CONTACT_ID" , columnDefinition="uniqueidentifier")
    private UUID ContactId;

    public Users() {

    }
    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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

    public byte[] getPassword() {
        return Password;
    }

    public void setPassword(byte[] password) {
        Password = password;
    }

    public UUID getContactId() {
        return ContactId;
    }

    public void setContactId(UUID contactId) {
        ContactId = contactId;
    }
}

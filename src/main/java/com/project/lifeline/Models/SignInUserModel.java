package com.project.lifeline.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class SignInUserModel {
    private UUID UserId;
    
    @Email
    @NotNull
    private String Email;

    @NotNull
    private String Password;

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
}

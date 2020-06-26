package com.project.lifeline.Models;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.util.UUID;

public class RegisterUserModel {
    private UUID UserId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String FirstName;

    @NotNull
    @Size(min = 1, max = 255)
    private String LastName;

    @Email
    @NotNull
    private String Email;

    @NotNull
    @Size(min = 1, max = 20)
    private String PhoneNumber;

    @NotNull
    private String Password;

    @NotNull
    private String ConfirmPassword;

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}

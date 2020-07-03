package com.project.lifeline.Models;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.*;

import java.util.UUID;

public class RegisterUserModel{
    private UUID UserId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String FirstName;

    @NotNull
    @Size(min = 1, max = 255)
    private String LastName;

    @Email(message = "Must be a valid e-mail address.")
    @NotNull
    private String Username;

    @Pattern(regexp="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Input does not match phone number format.")
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

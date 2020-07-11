package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.lifeline.Services.UsersService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void createNewUser() {

        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("");
        register.setLastName("");
        register.setPhoneNumber("");
        register.setPassword("");
        register.setUsername("");

        Users user = usersService.createNewUser(register);

        assertThat(user.getUserId()).isNull();


    }

    @Test
    void findAll() {
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
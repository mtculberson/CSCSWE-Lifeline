package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.lifeline.Services.UsersService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user).isNull();
        }

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
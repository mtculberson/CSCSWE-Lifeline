package com.project.lifeline.Services;
import java.util.*;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void createNewUser1() {

        //Test case 01-01: All inputs are empty
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
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser2() {

        //Test case 01-02: All fields are empty except the password field
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("");
        register.setLastName("");
        register.setPhoneNumber("");
        register.setPassword("123");
        register.setUsername("");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser3() {

        //Test case 01-03: All fields are empty except the password and phone number fields
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("");
        register.setLastName("");
        register.setPhoneNumber("1234567890");
        register.setPassword("123");
        register.setUsername("");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser4() {

        //Test case 01-04: All fields are empty except the password, email address (username) and phone number fields
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("");
        register.setLastName("");
        register.setPhoneNumber("qij12387681");
        register.setPassword("123");
        register.setUsername("asdjasdkjqwriu313i1j13l409134njetnerkpj(U()(90098@ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser5() {

        //Test case 01-05: Password, email address (username) and phone number fields
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("");
        register.setLastName("asdjasdkjqwriu31134njetnerkpj(U()(90098asjjjjjjjjjjjj");
        register.setPhoneNumber("1234567890");
        register.setPassword("123");
        register.setUsername("90098@ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser6() {

        //Test case 01-06: All fields are filled, email address has an incorrect format
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("nerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134" +
                "njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpja");
        register.setLastName("asdjasdkjqwriu31134njetnerkpj(U()(90098asjjjjjjjjjjjj");
        register.setPhoneNumber("1234567890");
        register.setPassword("123");
        register.setUsername("asdjasdkjqwriu313i1j13l409134njetnerkpj(U()(90098ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser7() {

        //Test case 01-07: All fields are filled, first name is >250 characters
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("nerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu" +
                "31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjaskldaldijkpqdkjijohwdijdjqiojdwwqjodwqkd89789q8w7dqw9");
        register.setLastName("asdjasdkjqwriu31134njetnerkpj(U()(90098asjjjjjjjjjjjj");
        register.setPhoneNumber("1234567890");
        register.setPassword("123");
        register.setUsername("90098@ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser8() {

        //Test case 01-08: All fields are formatted and filled out the way they were intended, except phone number format.
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("Ivan");
        register.setLastName("Escalona");
        register.setPhoneNumber("1234567abc");
        register.setPassword("123@gsu.com");
        register.setUsername("90098@ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNull();
        }
    }

    @Test
    void createNewUser9() {

        //Test case 01-09: Base case, all fields are formatted and filled out the way they were intended.
        RegisterUserModel register = new RegisterUserModel();
        register.setFirstName("Ivan");
        register.setLastName("Escalona");
        register.setPhoneNumber("1234567890");
        register.setPassword("123@gsu.com");
        register.setUsername("90098@ajidiajsd.com");

        Users user = new Users();

        try {
            user = usersService.createNewUser(register);
        } catch (Exception ex) {
            assertThat(user.getUserId()).isNotNull();
        }
    }
}
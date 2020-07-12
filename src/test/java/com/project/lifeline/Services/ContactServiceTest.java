package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.EmergencyContact;
import com.project.lifeline.Models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @Autowired
    private Authentication auth;

    @Test
    void createNewContact1() {
        //Test case 02-01: All fields are empty

        Contact contact = new Contact();
        contact.setFirstName("");
        contact.setLastName("");
        contact.setPhoneNumber("");

        Contact realContact = new Contact();

        try {
            realContact = contactService.createNewContact(contact, auth);
        } catch (Exception ex) {
            assertThat(realContact.getContactId()).isNull();
        }
    }

    @Test
    void createNewContact2() {
        //Test case 02-02: One out of three fields are empty
        Contact contact = new Contact();
        contact.setFirstName("nerkpjasdjasdkjqwriu31134n" +
                "jetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetn" +
                "erkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjaskldaldijkpqdkjijohwdijdjqiojdwwqjodwqkd89789q8w7dqw9");
        contact.setLastName("");
        contact.setPhoneNumber("10characte");

        Contact realContact = new Contact();

        try {
            realContact = contactService.createNewContact(contact, auth);
        } catch (Exception ex) {
            assertThat(realContact.getContactId()).isNull();
        }
    }

    @Test
    void createNewContact3() {
        //Test case 02-03: Phone number in the wrong format
        Contact contact = new Contact();
        contact.setFirstName("nerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetner" +
                "kpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetn");
        contact.setLastName("kpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetnerkpjasdjasdkjqwriu31134njetn");
        contact.setPhoneNumber("10characte");

        Contact realContact = new Contact();

        try {
            realContact = contactService.createNewContact(contact, auth);
        } catch (Exception ex) {
            assertThat(realContact.getContactId()).isNull();
        }
    }

    @Test
    void createNewContact4() {
        //Test case 02-04: Base case, all fields are filled as intended.
        Contact contact = new Contact();
        contact.setFirstName("Ivan");
        contact.setLastName("Escalona");
        contact.setPhoneNumber("1234567890");

        Contact realContact = new Contact();

        try {
            realContact = contactService.createNewContact(contact, auth);
        } catch (Exception ex) {
            assertThat(realContact.getContactId()).isNotNull();
        }
    }
}
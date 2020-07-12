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
}
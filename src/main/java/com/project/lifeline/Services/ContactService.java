package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.EmergencyContact;
import com.project.lifeline.Models.Users;
import com.project.lifeline.Repositories.ContactRepository;
import com.project.lifeline.Repositories.EmergencyContactRepository;
import com.project.lifeline.Repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private EmergencyContactService eService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Contact createNewContact(Contact model, Authentication auth){
        Contact contact = new Contact();
        contact.setFirstName(model.getFirstName());
        contact.setLastName(model.getLastName());
        contact.setPhoneNumber(model.getPhoneNumber());
        contact.setCreatedOn(LocalDateTime.now());
        this.contactRepository.save(contact);

        EmergencyContact eContact = new EmergencyContact();
        eContact.setContactId(contact.getContactId());

        String name = auth.getName();
        List<Users> users = usersService.findAll();

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(name)){
                eContact.setUserId(users.get(i).getUserId());
            }
        }

        this.emergencyContactRepository.save(eContact);

        return model;
    }

    public List<Contact> findAll() {

        Iterable<Contact> it = contactRepository.findAll();

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        it.forEach(e -> contacts.add(e));

        return contacts;
    }

    public List<Contact> findEmergencyContacts(Authentication auth) {

        List<Contact> contactService = this.findAll();
        List<EmergencyContact> emerContacts = eService.findAll();
        String name = auth.getName();
        List<Users> users = usersService.findAll();
        Users user = new Users();

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(name)){
                user = users.get(i);
            }
        }

        List<Contact> contacts = new ArrayList<Contact>();
        List<EmergencyContact> eContacts = new ArrayList<EmergencyContact>();

        for(int i = 0; i < emerContacts.size(); i++){
            if(emerContacts.get(i).getUserId().equals(user.getUserId())){
                eContacts.add(emerContacts.get(i));
            }
        }


        for(int i = 0; i < eContacts.size(); i++){
            for(int j = 0; j < contactService.size(); j++){
                if(contactService.get(j).getContactId().equals(eContacts.get(i).getContactId())){
                    contacts.add(contactService.get(j));
                }
            }
        }

        return contacts;
    }
}

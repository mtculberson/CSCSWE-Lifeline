package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.Users;
import com.project.lifeline.Repositories.ContactRepository;
import com.project.lifeline.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Users createNewUser(RegisterUserModel model) {
        Contact contact = new Contact();
        contact.setFirstName(model.getFirstName());
        contact.setLastName(model.getLastName());
        contact.setPhoneNumber(model.getPhoneNumber());
        this.contactRepository.save(contact);

        Users user = new Users();
        //user.setPassword(model.getPassword()); THIS NEEDS TO BE HASHED FIRST
        user.setEmail("tannerculberson.capstone@gmail.com");
        user.setContactId(contact.getContactId());
        this.usersRepository.save(user);

        return user;
    }
}

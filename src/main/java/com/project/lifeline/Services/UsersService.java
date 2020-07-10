package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.SignInUserModel;
import com.project.lifeline.Models.Users;
import com.project.lifeline.Repositories.ContactRepository;
import com.project.lifeline.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createNewUser(RegisterUserModel model) {
        Contact contact = new Contact();
        contact.setFirstName(model.getFirstName());
        contact.setLastName(model.getLastName());
        contact.setPhoneNumber(model.getPhoneNumber());
        contact.setCreatedOn(LocalDateTime.now());
        this.contactRepository.save(contact);

        Users user = new Users();
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setUsername(model.getUsername());
        user.setRole("USER");
        user.setContactId(contact.getContactId());
        this.usersRepository.save(user);

        return user;
    }

    public List<Users> findAll() {

        Iterable<Users> it = usersRepository.findAll();

        ArrayList<Users> users = new ArrayList<Users>();
        it.forEach(e -> users.add(e));

        return users;
    }

    public Users findUserByUsername(String username){
        List<Users> users = findAll();

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }

        return null;
    }

    public void updateUser(RegisterUserModel model){
        Contact contact = new Contact();
        contact.setContactId(model.getContactId());
        contact.setFirstName(model.getFirstName());
        contact.setLastName(model.getLastName());
        contact.setPhoneNumber(model.getPhoneNumber());
        contact.setCreatedOn(model.getCreatedOn());
        contact.setUpdatedOn(LocalDateTime.now());
        this.contactRepository.save(contact);

        Users user = new Users();
        user.setUserId(model.getUserId());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setUsername(model.getUsername());
        user.setContactId(model.getContactId());
        user.setRole("USER");
        this.usersRepository.save(user);
    }

    public void deleteUser(Users model){
        this.usersRepository.delete(model);
    }

}

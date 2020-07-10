package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.EmergencyContact;
import com.project.lifeline.Repositories.ContactRepository;
import com.project.lifeline.Repositories.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    public List<EmergencyContact> findAll() {

        Iterable<EmergencyContact> it = emergencyContactRepository.findAll();

        ArrayList<EmergencyContact> eContacts = new ArrayList<EmergencyContact>();
        it.forEach(e -> eContacts.add(e));

        return eContacts;
    }

    public void deleteEmergencyContacts(UUID userId){

        List<EmergencyContact> eContacts = this.findAll();
        List<Contact> contacts = contactService.findAll();
        ArrayList<EmergencyContact> myeContact = new ArrayList<EmergencyContact>();
        ArrayList<Contact> myContact = new ArrayList<Contact>();

        for(int i = 0; i < eContacts.size(); i++){
            if(eContacts.get(i).getUserId().equals(userId)){
                myeContact.add(eContacts.get(i));
            }
        }

        for(int i = 0; i < contacts.size(); i++){
            for(int j = 0; j < myeContact.size(); j++)
                if(myeContact.get(j).getContactId().equals(contacts.get(i).getContactId()))
                    myContact.add(contacts.get(i));
        }

        for(int i = 0; i < myeContact.size(); i++){
            this.emergencyContactRepository.delete(myeContact.get(i));
        }

        for(int i = 0; i < myContact.size(); i++){
            this.contactRepository.delete(myContact.get(i));
        }

    }
}

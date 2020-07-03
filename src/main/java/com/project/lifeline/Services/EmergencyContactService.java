package com.project.lifeline.Services;

import com.project.lifeline.Models.Contact;
import com.project.lifeline.Models.EmergencyContact;
import com.project.lifeline.Repositories.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    public List<EmergencyContact> findAll() {

        Iterable<EmergencyContact> it = emergencyContactRepository.findAll();

        ArrayList<EmergencyContact> eContacts = new ArrayList<EmergencyContact>();
        it.forEach(e -> eContacts.add(e));

        return eContacts;
    }
}

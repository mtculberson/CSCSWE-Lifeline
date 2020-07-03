package com.project.lifeline.Repositories;

import com.project.lifeline.Models.EmergencyContact;
import com.project.lifeline.Models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmergencyContactRepository extends CrudRepository<EmergencyContact, UUID> {
}

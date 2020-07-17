package com.project.lifeline.Repositories;

import com.project.lifeline.Models.Emergency;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmergencyRepository extends CrudRepository<Emergency, UUID> {
}

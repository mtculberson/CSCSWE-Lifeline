package com.project.lifeline.Repositories;

import com.project.lifeline.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}

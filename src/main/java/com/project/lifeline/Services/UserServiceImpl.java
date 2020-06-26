package com.project.lifeline.Services;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private UserRepository userRepository;

    public User createNewUser(RegisterUserModel model) {
        User user = new User();
        //this.userRepository.save(user);
        //UUID id = user.getUserId(); //id should have a value now.
        return user;
    }
}

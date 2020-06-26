package com.project.lifeline.Services;

import com.project.lifeline.Models.RegisterUserModel;
import com.project.lifeline.Models.User;

public interface UserService {
    public User createNewUser(RegisterUserModel model);
}

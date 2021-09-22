package com.vladimir1506.rest.controller;

import com.vladimir1506.rest.model.User;
import com.vladimir1506.rest.repository.implementation.UserImpl;

import java.util.List;

public class UserController {
    private final UserImpl userImpl;

    public UserController() {
        this.userImpl = new UserImpl();
    }

    public List<User> getAllUsers() {
        return userImpl.getAll();
    }

    public User getUserById(Long id) {
        return userImpl.getById(id);
    }

    public User saveUser(User user) {
        return userImpl.save(user);
    }

    public void deleteUser(Long id) {
        userImpl.delete(id);
    }
}

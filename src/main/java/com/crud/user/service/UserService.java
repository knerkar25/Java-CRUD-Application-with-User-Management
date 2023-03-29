package com.crud.user.service;

import com.crud.user.model.User;
import com.crud.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int userId) {
        User user;
        try {
            user = userRepository.findById(userId).get();
        } catch (Exception exception) {
            user = null;
        }
        return user;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}

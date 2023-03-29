package com.crud.user.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User ironman = null;

    @BeforeEach
    void init() {
        ironman = new User(1, "ironman", 21);
        userRepository.save(ironman);
    }

    @Test
    void shouldBeAbleToSaveAndGetUserDetails() {
        User user = userRepository.findById(1).get();

        assertEquals(ironman.getId(), user.getId());
        assertEquals(ironman.getName(), user.getName());
        assertEquals(ironman.getAge(), user.getAge());
    }

    @Test
    void shouldBeAbleToUpdateUser() {
        User updatedIronman = new User(1, "ironman", 31);

        userRepository.save(updatedIronman);
        User user = userRepository.findById(1).get();

        assertEquals(updatedIronman.getId(), user.getId());
        assertEquals(updatedIronman.getName(), user.getName());
        assertEquals(updatedIronman.getAge(), user.getAge());
    }

    @Test
    void shouldBeAbleToDeleteUser() {
        int ironmanId = 1;

        userRepository.deleteById(ironmanId);
        List<User> users = (List<User>) userRepository.findAll();

        assertEquals(users.size(), 0);
    }

    @AfterEach
    void drop() {
        userRepository.deleteAll();
    }
}

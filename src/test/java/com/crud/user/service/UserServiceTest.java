package com.crud.user.service;

import com.crud.user.model.User;
import com.crud.user.model.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldBeAbleToCallFindAllByIdMethodOfUserRepositoryToGetUserDetails() {
        userService.getUserById(1);
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void shouldBeAbleToCallFindAllMethodOfUserRepositoryToGetAllUserDetails() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldBeAbleToCallSaveMethodOfUserRepositoryToCreateAUser() {
        User ironman = new User(1, "ironman", 21);
        userService.create(ironman);
        verify(userRepository, times(1)).save(ironman);
    }

    @Test
    void shouldBeAbleToCallSaveMethodOfUserRepositoryToUpdateUserDetails() {
        User ironman = new User(1, "ironman", 32);
        userService.update(ironman);
        verify(userRepository, times(1)).save(ironman);
    }

    @Test
    void shouldBeAbleToCallDeleteMethodOfUserRepositoryToDeleteAUser() {
        userService.deleteUserById(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}

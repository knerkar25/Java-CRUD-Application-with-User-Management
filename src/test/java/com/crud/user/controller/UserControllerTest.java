package com.crud.user.controller;

import com.crud.user.model.User;
import com.crud.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class})
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User ironman;

    @BeforeEach
    public void init() {
        ironman = new User(1, "ironman", 21);
    }

    @Test
    void shouldBeAbleToCreateUserDetails() throws Exception {
        when(userService.create(ironman)).thenReturn(ironman);
        String ironmanJson = new ObjectMapper().writeValueAsString(ironman);

        ResultActions result = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(ironmanJson));

        result.andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void shouldBeAbleToGetUserDetailsById() throws Exception {
        when(userService.getUserById(1)).thenReturn(ironman);

        ResultActions result = mockMvc.perform(get("/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("ironman")).andExpect(jsonPath("$.age").value(21)).andDo(print());
    }

    @Test
    void shouldBeAbleToUpdateUserDetails() throws Exception {
        when(userService.update(ironman)).thenReturn(ironman);
        String ironmanJson = new ObjectMapper().writeValueAsString(ironman);

        ResultActions result = mockMvc.perform(put("/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON).content(ironmanJson));

        result.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void shouldBeAbleToDeleteUserByUserId() throws Exception {
        ResultActions result = mockMvc.perform(delete("/users/{userId}", 1));

        result.andExpect(status().isOk()).andDo(print());
    }
}

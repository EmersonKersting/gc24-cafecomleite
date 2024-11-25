package com.caldeira.projetofinal.user.controllers;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private UserResponseModel sampleUser;

    @BeforeEach
    void setup() {
        sampleUser = new UserResponseModel();
        sampleUser.setId(UUID.randomUUID());
        sampleUser.setFirstName("João");
        sampleUser.setLastName("Victor");
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        when(userService.getAll()).thenReturn(Collections.singletonList(sampleUser));
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleUser.getId().toString()))
                .andExpect(jsonPath("$[0].firstName").value(sampleUser.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(sampleUser.getLastName()));
    }

    @Test
    void shouldReturnUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        UserResponseModel sampleUser = new UserResponseModel();
        sampleUser.setId(userId);
        sampleUser.setFirstName("João");
        sampleUser.setLastName("Victor");
        sampleUser.setCreationDate(LocalDateTime.now());

        when(userService.getById(userId)).thenReturn(sampleUser);
        mockMvc.perform(get("/users/get-by-id/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.firstName").value("João"))
                .andExpect(jsonPath("$.lastName").value("Victor"));
        verify(userService, times(1)).getById(userId);
    }

    @Test
    void shouldReturnNotFoundForNonExistentUserById() throws Exception {
        UUID userId = UUID.randomUUID();

        when(userService.getById(userId)).thenReturn(null);
        mockMvc.perform(get("/users/get-by-id/{id}", userId))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).getById(userId);
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserRequestModel requestModel = new UserRequestModel();
        requestModel.setFirstName("João");
        requestModel.setLastName("Victor");

        when(userService.create(any(UserRequestModel.class))).thenReturn(sampleUser);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"João\", \"lastName\": \"Victor\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("João"))
                .andExpect(jsonPath("$.lastName").value("Victor"));
        verify(userService, times(1)).create(any(UserRequestModel.class));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UserRequestModel updateModel = new UserRequestModel("Donnie", "Darko");
        UserResponseModel updatedUser = new UserResponseModel(userId, "Donnie", "Darko", LocalDateTime.now());

        when(userService.update(userId, updateModel)).thenReturn(updatedUser);
        mockMvc.perform(put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Donnie\", \"lastName\": \"Darko\"}"))
                .andExpect(status().isOk()) // Espera 200 OK
                .andExpect(jsonPath("$.firstName").value("Donnie"))
                .andExpect(jsonPath("$.lastName").value("Darko"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentUserUpdate() throws Exception {
        UUID userId = UUID.randomUUID();
        UserRequestModel updateModel = new UserRequestModel("Donnie", "Darko");

        when(userService.update(userId, updateModel)).thenReturn(null);
        mockMvc.perform(put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Donnie\", \"lastName\": \"Darko\"}"))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).update(userId, updateModel);
    }

    @Test
    void shouldDeleteUserById() throws Exception {
        when(userService.deleteById(sampleUser.getId())).thenReturn(true);
        mockMvc.perform(delete("/users/delete-by-id/" + sampleUser.getId())).andExpect(status().isOk());
        verify(userService, times(1)).deleteById(sampleUser.getId());
    }

    @Test
    void shouldReturnNotFoundForNonExistentUserDeletion() throws Exception {
        mockMvc.perform(delete("/users/delete-by-id/" + UUID.randomUUID())).andExpect(status().isNotFound());
    }
}

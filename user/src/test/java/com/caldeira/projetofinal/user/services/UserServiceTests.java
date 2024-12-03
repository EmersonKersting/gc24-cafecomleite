package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.entities.UserEntity;
import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    private UserRepository userRepository;
    private UserRequestValidator userRequestValidator;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userRequestValidator = mock(UserRequestValidator.class);
        userService = new UserService(userRepository, userRequestValidator);
    }

    @Test
    void shouldGetAllUsers() {
        UserEntity user1 = new UserEntity(UUID.randomUUID(), "João", "Victor", LocalDateTime.now());
        UserEntity user2 = new UserEntity(UUID.randomUUID(), "Donnie", "Darko", LocalDateTime.now());
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserResponseModel> users = userService.getAll();

        assertEquals(2, users.size());
        assertEquals("João", users.get(0).getFirstName());
        assertEquals("Donnie", users.get(1).getFirstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetUserById() {
        UUID id = UUID.randomUUID();
        UserEntity user = new UserEntity(id, "João", "Victor", LocalDateTime.now());
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserResponseModel response = userService.getById(id);

        assertNotNull(response);
        assertEquals("João", response.getFirstName());
        assertEquals("Victor", response.getLastName());
        verify(userRepository, times(1)).findById(id);
    }


    @Test
    void shouldCreateUser() {
        UserRequestModel request = new UserRequestModel("João", "Victor");
        UserEntity savedUser = new UserEntity(UUID.randomUUID(), "João", "Victor", LocalDateTime.now());
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(savedUser);

        UserResponseModel response = userService.create(request);

        assertNotNull(response);
        assertEquals("João", response.getFirstName());
        assertEquals("Victor", response.getLastName());
        verify(userRequestValidator, times(1)).validate(request);
        verify(userRepository, times(1)).save(Mockito.any(UserEntity.class));
    }

    @Test
    void shouldUpdateUser() {
        UUID id = UUID.randomUUID();
        UserRequestModel request  = new UserRequestModel("João", "Victor Atualizado");
        UserEntity existingUser = new UserEntity(id, "João", "Victor", LocalDateTime.now());
        UserEntity updatedUser = new UserEntity(id, "João", "Victor Atualizado", LocalDateTime.now());
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(updatedUser);

        UserResponseModel response = userService.update(id, request);

        assertNotNull(response);
        assertEquals("João", response.getFirstName());
        assertEquals("Victor Atualizado", response.getLastName());
        verify(userRequestValidator, times(1)).validate(request);
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void shouldDeleteUserById() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(true);
        boolean result = userService.deleteById(id);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldReturnFalseWhenDeletingNonExistentUser() {
        UUID id = UUID.randomUUID();
        when(userRepository.existsById(id)).thenReturn(false);
        boolean result = userService.deleteById(id);

        assertFalse(result);
        verify(userRepository, times(1)).existsById(id);
        verify(userRepository, never()).deleteById(id);
    }
}

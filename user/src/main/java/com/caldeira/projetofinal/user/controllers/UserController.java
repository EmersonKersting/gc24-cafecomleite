package com.caldeira.projetofinal.user.controllers;

import main.java.com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseModel>> getAll() {

        return ResponseEntity.ok(users);
    }
}
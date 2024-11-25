package com.caldeira.projetofinal.user.controllers;


import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseModel>> getAll() {
        List<UserResponseModel> users = userService.getAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserResponseModel> getById(@PathVariable UUID id) {
        UserResponseModel user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponseModel> create(@RequestBody UserRequestModel model) {
        UserResponseModel createdUser = userService.create(model);
        return ResponseEntity.status(201).body(createdUser);
    }

}
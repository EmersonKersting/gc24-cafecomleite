package com.caldeira.projetofinal.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.caldeira.projetofinal.user.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
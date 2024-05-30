package com.example.afroseats_userService.controllers;

import com.example.afroseats_userService.dto.UserDTO;
import com.example.afroseats_userService.service.impl.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final CreateUserService createUserService;

    public UserRestController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = createUserService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
}

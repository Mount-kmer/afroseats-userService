package com.example.afroseats_userService.service.impl;

import com.example.afroseats_userService.dto.UserDTO;
import com.example.afroseats_userService.dto.mapper.UserMapper;
import com.example.afroseats_userService.entity.Roles;
import com.example.afroseats_userService.entity.User;
import com.example.afroseats_userService.repository.RoleRepository;
import com.example.afroseats_userService.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    public CreateUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        Set<String> roleNames = userDTO.getRoles();

        if (roleNames == null || roleNames.isEmpty()) {
            roleNames = new HashSet<>();
            roleNames.add("USER");
        }

        Set<Roles> roleSet = roleNames.stream()
                .map(roleName -> {
                    Optional<Roles> roleOpt = roleRepository.findByName(roleName);
                    System.out.println("Role fetched in service: " + roleOpt.orElse(null)); // Added for debugging
                    return roleOpt.orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                })
                .collect(Collectors.toSet()
        );

        User user = UserMapper.toEntity(userDTO, roleSet);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.userDTO(savedUser);
    }
}

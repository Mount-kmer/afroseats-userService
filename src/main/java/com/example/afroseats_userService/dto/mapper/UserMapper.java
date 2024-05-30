package com.example.afroseats_userService.dto.mapper;


import com.example.afroseats_userService.dto.UserDTO;
import com.example.afroseats_userService.entity.Roles;
import com.example.afroseats_userService.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO userDTO(User user) {
       return new UserDTO(
               user.getFirstName(),
               user.getLastName(),
               user.getEmail(),
               user.getPhoneNumber(),
               user.getPassword(),
               user.getDateOfBirth(),
                user.getGender(),
               user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet())
       );
    }

    public  static User toEntity(UserDTO userDTO, Set<Roles> roleSet) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRoles(roleSet);
        return user;
    }
}

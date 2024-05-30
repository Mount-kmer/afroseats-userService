
package com.example.afroseats_userService;

import com.example.afroseats_userService.dto.UserDTO;
import com.example.afroseats_userService.entity.Roles;
import com.example.afroseats_userService.entity.User;
import com.example.afroseats_userService.repository.RoleRepository;
import com.example.afroseats_userService.repository.UserRepository;
import com.example.afroseats_userService.service.impl.CreateUserService;
import com.example.afroseats_userService.utility.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CreateUserService userService;

    @BeforeEach
    public void setUp() {
        // Manually instantiate the mocks
        userRepository = Mockito.mock(UserRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);

        // Manually inject mocks into the service
        userService = new CreateUserService(userRepository, roleRepository, passwordEncoder);

        // Setting up a mock role
        Roles userRole = new Roles();
        userRole.setId(1L);
        userRole.setName("USER");
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(userRole));
        System.out.println("Mock setup: " + roleRepository.findByName("USER"));
    }

    @Test
    public void testCreateUser() {
        // Setting up test data
        Date dateOfBirth = new Date();
        UserDTO userDTO = new UserDTO(
                "Thomas", "Shelby",
                "thomas.shelby@gmail.com",
                "1234567890", "Password1!",
                dateOfBirth, Gender.MALE,
                Set.of("USER")
        );

        // Mocking the password encoding and user save operations
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword("encodedPassword");
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        Roles role = new Roles();
        role.setId(1L);
        role.setName("USER");
        user.setRoles(Set.of(role));

        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<Roles> roleOpt = roleRepository.findByName("USER");
        System.out.println("Role fetched in test: " + roleOpt.orElse(null));

        UserDTO savedUser = userService.createUser(userDTO);

        assertEquals(userDTO.getFirstName(), savedUser.getFirstName());
        assertEquals(userDTO.getLastName(), savedUser.getLastName());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
        assertEquals(userDTO.getPhoneNumber(), savedUser.getPhoneNumber());
        assertEquals(userDTO.getDateOfBirth(), savedUser.getDateOfBirth());
        assertEquals(userDTO.getGender(), savedUser.getGender());
        assertEquals(userDTO.getRoles(), savedUser.getRoles());

        verify(roleRepository, times(3)).findByName("USER");
        verify(passwordEncoder, times(1)).encode(userDTO.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }
}

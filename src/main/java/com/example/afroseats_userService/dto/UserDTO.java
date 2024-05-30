package com.example.afroseats_userService.dto;

import com.example.afroseats_userService.utility.Gender;
import com.example.afroseats_userService.utility.UniqueEmailAddress;
import com.example.afroseats_userService.utility.ValidAge;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    @NotEmpty(message = "First name cannot be empty")
    private final String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private final String lastName;

    @UniqueEmailAddress
    @NotEmpty(message = "Email cannot be empty")
    private final String email;

    @NotEmpty(message = "Phone Number cannot be empty")
    private final String phoneNumber;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$", message = "Password must have one upper case, one lower case, one digit, one special characters and should be a  minimum  of 8 characters.")
    @NotEmpty(message = "Password cannot be empty")
    private final String password;

    @ValidAge(value = 18)
    private final Date dateOfBirth;

    @NotEmpty(message ="gender cannot be empty")
    private final Gender gender;

    private Set<String> roles = new HashSet<>();

    public UserDTO(String firstName, String lastName, String email, String phoneNumber, String password, Date dateOfBirth, Gender gender, Set<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.roles = roles != null ? roles : new HashSet<>();
    }

//    public UserDTO() {
//        this.roles = new HashSet<>();
//
//    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<String> getRoles() {
        return roles;
    }
}

package com.example.afroseats_userService.service;

import com.example.afroseats_userService.entity.Roles;
import com.example.afroseats_userService.entity.User;
import com.example.afroseats_userService.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AfroSeatsUserDetails implements UserDetails {

    private final User user;
    private final UserRepository userRepository;

    public AfroSeatsUserDetails(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Roles> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccount_non_expired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccount_non_locked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  user.getIsEnabled();
    }
}

package com.example.afroseats_userService.repository;

import com.example.afroseats_userService.entity.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends CrudRepository<Roles, Long> {

    Optional<Roles> findByName(String name);
}

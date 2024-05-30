package com.example.afroseats_userService.repository;

import com.example.afroseats_userService.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {

   @Query("select u from User  u where u.email = :email")
    User searchByEmail(@Param("email") String email);

    @Query("select u from User u where u.id = :id")
    User searchById(@Param("id") int id);
}

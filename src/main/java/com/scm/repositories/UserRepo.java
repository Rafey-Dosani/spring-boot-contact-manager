package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;


@Repository
public interface  UserRepo extends JpaRepository<User, String> {
        //extra methods db related
        //1.custom query methods
        //2.custom finder methods 

        Optional<User> findByEmail(String email);
}

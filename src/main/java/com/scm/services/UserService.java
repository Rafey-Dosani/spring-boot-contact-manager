package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;




public interface UserService {

    User saveUser (User user);


    Optional<User>getUserByID(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExisByEmail(String Email);

    List<User> getAllUser();

    User getUserByEmail(String email);
    //Add more methods here related to logic(service)

}

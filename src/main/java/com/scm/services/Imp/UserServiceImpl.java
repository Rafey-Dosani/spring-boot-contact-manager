package com.scm.services.Imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        //User id have to generate 
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);

        //password Encode
        
        //user setPassword(userID)
       
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role 
        user.setRoleList(List.of(AppConstants.ROLE_USER));


        // logger.info(user.getProviderId().toString());

         logger.info("ProviderId: {}", user.getProviderId());
       return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserByID(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       User user2= userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("USer not found"));
       //Update user2 from by user 

       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
         user2.setEnabled(user.isEnabled());
         user2.setEnabled(user.isEnabled());
         user2.setEmailVerified(user.isEmailVerified());
         user2.setPhoneverified(user.isPhoneverified());
         user2.setProviders(user.getProviders());
         user2.setProviderId(user.getProviderId());

         //save the value in database
         User save=userRepo.save(user2);
         return Optional.ofNullable(save);
          

    }

    @Override
    public void deleteUser(String id) {
      User user2= userRepo.findById(id)
      .orElseThrow(()->new ResourceNotFoundException("USer not found"));
        userRepo.delete(user2);
    }

   @Override
public boolean isUserExist(String userId) {
    return userRepo.findById(userId).isPresent();
}

    @Override
    public boolean isUserExisByEmail(String email) {
         User user2= userRepo.findByEmail(email).orElse(null);
        return user2!=null ? true : false;
    
    }

    @Override
    public List<User> getAllUser() {
       return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
       return userRepo.findByEmail(email).orElse(null);
    }

}

package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    


    Logger logger=LoggerFactory.getLogger(UserController.class);

    //User Dashboard
    @GetMapping("/dashboard")
    public String userDhashboard() {
        return "user/dashboard";
    }
    

    //User profile
     @GetMapping("/profile")
    public String userprfile(Model model ,Authentication authentication) {
       

        String username=  Helper.getEmailOfLoggedInUser(authentication);

        
        logger.info("User logged in: {}",username);

        User user =userService.getUserByEmail(username);
        //fetch data from DB
      
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser",user);

        System.out.println("USer profile");
        return "user/profile";
    }

    //user add contact page 


    //user view contact 

    //user edit contact



}

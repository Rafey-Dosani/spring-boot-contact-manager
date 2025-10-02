package com.scm.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForms;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class homecontoller {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
            return "redirect:/home";
    }



    @GetMapping("/home")
    public String home(){

        return "home";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }

     @GetMapping("/services")
    public String services(){

        return "services";
    }

     @GetMapping("/contact")
    public String contact(){

        return "contact";
    }

    // This showing Login PAge 
     @GetMapping("/login")
    public String login(){

        return "login";
    }

    



     @GetMapping("/register")
    public String register(Model model ){

        UserForms userform =new UserForms();
       
        model.addAttribute("userform",userform);
    
        return "register";
    }

    //Processing Registratin 
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("userform") UserForms userform, BindingResult rBindingResult, HttpSession session,Model model){
        System.out.println("Registered");
        //   1)fetch data 
        //User Form
        System.out.println(userform);


        //   2)validate data 

        if (rBindingResult.hasErrors()) {
        // Add the userform back to the model before returning
        model.addAttribute("userform", userform);
        return "register";
    }


        //    3)save to database
        // userService
        

        // User user =User.builder()
        // .name(userform.getName())
        // .email(userform.getEmail())
        // .password(userform.getPassword())
        // .about(userform.getAbout())
        // .phoneNumber(userform.getPhoneNumber())
        // .build();


        User user = new User();
         user.setName(userform.getName());
       user.setEmail(userform.getEmail());
       user.setPassword(userform.getPassword());
       user.setAbout(userform.getAbout());
        user.setPhoneNumber(userform.getPhoneNumber());
        user.setEnabled(true);
        user.setProfilePic("https://media.istockphoto.com/id/522855255/vector/male-profile-flat-blue-simple-icon-with-long-shadow.jpg?s=612x612&w=0&k=20&c=EQa9pV1fZEGfGCW_aEK5X_Gyob8YuRcOYCYZeuBzztM=");
        
         User savUser= userService.saveUser(user);

         System.out.println("USer saved");

        //message=Registration Sucessfull 

      Message message = Message.builder()
                         .content("Registration Successful")
                         .type(MessageType.green)
                         .build();
        session.setAttribute("message", message);



        //redirect
        return "redirect:/register";
    }

}

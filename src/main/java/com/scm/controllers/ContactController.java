package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger=LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;
    
    // Add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactform = new ContactForm();
        contactform.setFavorite(false
        );
        model.addAttribute("contactForm", contactform);
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,
                              BindingResult result,
                              Authentication authetication,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        // Validate the form
        if (result.hasErrors()) {

            result.getAllErrors().forEach(error->logger.info(error.toString()));
    
            // send back to form with errors
            model.addAttribute("contactForm", contactForm);
            model.addAttribute("message", Message.builder()
                                                 .content("Please correct the following errors")
                                                 .type(MessageType.red)
                                                 .build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authetication);
        User user = userService.getUserByEmail(username);



        //process contact picture 
        
        //image process
   logger.info("File information:", contactForm.getContactImage().getOriginalFilename());


   //image upload code

   String filename=UUID.randomUUID().toString();
   String fileURL=imageService.uploadImage(contactForm.getContactImage(),filename);

        // form ---> contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAdress(contactForm.getAddress());
        contact.setDescription(contactForm.getDiscription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact);

        System.out.println(contactForm);

        // Set success message to be displayed after redirect
        redirectAttributes.addFlashAttribute("message",
            Message.builder()
                   .content("You have successfully added a new contact")
                   .type(MessageType.green)
                   .build()
        );

        // Redirect after save
        return "redirect:/user/contacts/add";
    }



    //view contacts

    @GetMapping
    public String viewContacts ( 
        @RequestParam(value="page" , defaultValue = "0") int page,
        @RequestParam(value="size", defaultValue = "6") int size,
        @RequestParam(value="sortBy", defaultValue="name") String sortBy,
        @RequestParam(value="sortDirection" , defaultValue="asc") String sortDirection,
    
    Model model , Authentication authentication){


       String username =  Helper.getEmailOfLoggedInUser(authentication);

       User user =userService.getUserByEmail(username);

        //load all the user contacts
        Page<Contact> pageContact=contactService.getByUser(user,page,size , sortBy , sortDirection); 

        

       
        model.addAttribute("pageContact",pageContact);
        model.addAttribute("pageSize", AppConstants.pageSize);

        return "user/contacts";
    }

}

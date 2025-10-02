package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.entities.Contact;
import com.scm.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ContactService contactService;

    @GetMapping("/contacts/{contactId}")
    public Contact geContact(@PathVariable String contactId){

        return contactService.getById(contactId);
        
    }


    @DeleteMapping("/contacts/{contactId}")
public ResponseEntity<String> deleteContact(@PathVariable String contactId) {
    contactService.delete(contactId);
    return ResponseEntity.ok("Contact deleted successfully");
}



}

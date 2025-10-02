package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {

   

    //save contact
    Contact save(Contact contact);

    //update contact
    Contact update(Contact contact);

    //get contact 
    List<Contact>getAll();

    //get Cotact by id
    Contact getById(String id);

    //delete contact 
    void delete(String id);

    //search contact
    List<Contact>search(String name ,String email,String phoneNumber);

    //get by user id
    List<Contact>getByUserId(String userid);

    Page<Contact>getByUser(User user, int page , int size, String sortField , String sortDirection);

}

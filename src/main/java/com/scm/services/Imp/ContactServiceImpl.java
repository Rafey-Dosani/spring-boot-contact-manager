package com.scm.services.Imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo; 


    @Override
    public Contact save(Contact contact) {
      String contactId=UUID.randomUUID().toString();
      contact.setId(contactId);
      return contactRepo.save(contact);

    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
       return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
       return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not found with given id"));
    }

    @Override
    public void delete(String id) {
       var Contact= contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not found with given id"));
 
        contactRepo.delete(Contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getByUserId(String userid) {
       return contactRepo.findByUserId(userid);
    }

    @Override
    public Page<Contact> getByUser(User user, int page , int size ,String sortby, String direction ) {
      
      Sort sort= direction.equals("desc")? Sort.by(sortby).descending() : Sort.by(sortby).ascending();
      var pageble= PageRequest.of(page,size, sort);

      

       return contactRepo.findByUser(user, pageble);
    }

}

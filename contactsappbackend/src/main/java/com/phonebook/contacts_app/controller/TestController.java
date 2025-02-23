package com.phonebook.contacts_app.controller;

import com.phonebook.contacts_app.model.Contact;
import com.phonebook.contacts_app.service.ContactService;
import com.phonebook.contacts_app.service.ContactServiceFactory;
import com.phonebook.contacts_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    ContactService contactService;

    ContactServiceFactory contactServiceFactory;
    @Autowired
    UserService userService;

    public TestController(ContactServiceFactory contactServiceFactory,UserService userService){
        this.contactServiceFactory=contactServiceFactory;
        this.userService=userService;
    }


    @GetMapping
            ("/checkUser")
    public ResponseEntity<?> checkUserExists(@RequestParam String username) {
        boolean exists = userService.validateuser(username);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    @GetMapping("/getContacts")
    public ResponseEntity<List<Contact>> getContacts( @RequestParam(name = "username") String username,
            @RequestParam(name = "channel", defaultValue = "DATABASE") String channel) {
        contactService=contactServiceFactory.getStrategy(channel);
        List<Contact> contacts = contactService.getContacts(username);
        System.out.println("succesfully fetched contacts");
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/editContact")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact,
                                           @RequestParam(name = "channel", defaultValue = "DATABASE") String channel) {
        contactService=contactServiceFactory.getStrategy(channel);
        Contact updatedContact = contactService.editContact(contact);
        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        } else {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }
    }

    @DeleteMapping("/deletecontact")
    public ResponseEntity<String> deleteContact(@RequestParam int id,@RequestParam(name = "channel", defaultValue = "DATABASE") String channel) {
        contactService=contactServiceFactory.getStrategy(channel);
        boolean isDeleted = contactService.deleteContact(id);

        if (isDeleted) {
            return ResponseEntity.ok("Contact deleted successfully");
        } else {
            return ResponseEntity.status(404).body("something wrong");
        }
    }


    @PostMapping("/addContact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact,@RequestParam(name="channel", defaultValue = "DATABASE") String channel){
        contactService=contactServiceFactory.getStrategy(channel);
       Contact savedContact= contactService.addContact(contact);
       return new ResponseEntity<>(savedContact,HttpStatus.CREATED);
    }


}

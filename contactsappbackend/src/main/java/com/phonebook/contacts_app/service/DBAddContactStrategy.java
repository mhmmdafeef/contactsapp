package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;
import com.phonebook.contacts_app.model.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DBAddContactStrategy implements AddContactStrategy {


    @Autowired
    ContactRepository contactRepository;
    @Override
    public Contact  addContact(Contact contact) {
        return contactRepository.save(contact) ;
    }
}

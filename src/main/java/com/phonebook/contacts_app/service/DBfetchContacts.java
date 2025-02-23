package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;
import com.phonebook.contacts_app.model.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DBfetchContacts implements FetchContactsStrategy {

    private final ContactRepository contactRepository;
    public DBfetchContacts(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public List<Contact> fetchContacts(String username) {
        return contactRepository.findByUsername(username);
    }
}

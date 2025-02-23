package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> getContacts(String username);

    public Contact addContact( Contact contact);

    public Contact editContact( Contact contact);

    public Boolean deleteContact( int id);
}

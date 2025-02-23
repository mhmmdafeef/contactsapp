package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;

import java.util.List;

public interface FetchContactsStrategy {
    List<Contact> fetchContacts(String username);
}

package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;
import com.phonebook.contacts_app.model.ContactRepository;
import org.springframework.stereotype.Component;


@Component
public class DBEditContactStrategy implements EditContactStrategy{

    public DBEditContactStrategy(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    ContactRepository contactRepository;
    @Override
    public Contact editContact(Contact contact) {
        return contactRepository.findById(contact.getId())
                .map(existingContact -> {

                    existingContact.setContactNumber(contact.getContactNumber());
                    existingContact.setName(contact.getName()); // Update contact number
                    return contactRepository.save(existingContact); // Save changes
                })
                .orElse(null);
    }
}

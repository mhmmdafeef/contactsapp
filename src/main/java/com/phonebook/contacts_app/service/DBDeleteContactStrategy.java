package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.ContactRepository;
import org.springframework.stereotype.Component;


@Component
public class DBDeleteContactStrategy implements DeleteContactStrategy{

    ContactRepository contactRepository;

    public DBDeleteContactStrategy(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Boolean deleteContact(int id) {
        try {
            contactRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
}

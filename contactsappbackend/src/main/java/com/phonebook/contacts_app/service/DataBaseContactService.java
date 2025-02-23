package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("DATABASE")
public class DataBaseContactService implements ContactService {


    private  FetchContactsStrategy fetchContacts;

    private  AddContactStrategy addContactStrategy;

    private EditContactStrategy editContactStrategy;

    private DeleteContactStrategy deleteContactStrategy;


    @Autowired
    public DataBaseContactService(DBAddContactStrategy dbAddContactStrategy,
                                  DBfetchContacts dBfetchContacts,
                                  DBEditContactStrategy dbeditContactStrategy,
                                  DBDeleteContactStrategy dbDeleteContactStrategy ){
        this.fetchContacts=dBfetchContacts;
        this.addContactStrategy=dbAddContactStrategy;
        this.editContactStrategy=dbeditContactStrategy;
        this.deleteContactStrategy=dbDeleteContactStrategy;
    }



    public DataBaseContactService(){

    }

    public List<Contact> getContacts(String username){

        return fetchContacts.fetchContacts(username);
    }

    public Contact addContact( Contact contact){

        return addContactStrategy.addContact(contact);
    }


    public Contact editContact( Contact contact){

        return editContactStrategy.editContact(contact);
    }

    public Boolean deleteContact( int id){

        return deleteContactStrategy.deleteContact(id);
    }
}

package com.phonebook.contacts_app.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {


    List<Contact> findByUsername(String username);


    Optional<Contact> findByName(String name);


}

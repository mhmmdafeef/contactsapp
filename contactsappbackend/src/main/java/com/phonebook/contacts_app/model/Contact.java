package com.phonebook.contacts_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="contact_number")
    private String contactNumber;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    // Many contacts belong to one user
    @Column(name = "username", nullable = false)

    private String username;

    // Default constructor
    public Contact() {}

    // Parameterized constructor
    public Contact(int id,String contactNumber, String name, String user) {
        this.contactNumber = contactNumber;
        this.name = name;
        this.username = user;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }



}





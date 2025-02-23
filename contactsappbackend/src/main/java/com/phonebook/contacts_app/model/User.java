package com.phonebook.contacts_app.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
    @Table(name = "users")

    public class User {


@Id
        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @OneToMany(mappedBy = "username", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Contact> contacts;

        private boolean enabled;

        public User(String username, String password, boolean enabled) {
            this.username = username;
            this.password = password;
            this.enabled = enabled;
        }

        public User(){

        }

        // Getters and Setters


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }



    }


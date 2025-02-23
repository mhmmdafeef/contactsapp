package com.phonebook.contacts_app.service;

import com.phonebook.contacts_app.model.User;
import com.phonebook.contacts_app.model.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public  UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public Boolean validateuser(String username){
        User user=userRepository.findByUsername(username);

        if(user!=null){
            return true;
        }
        return false;
    }
}

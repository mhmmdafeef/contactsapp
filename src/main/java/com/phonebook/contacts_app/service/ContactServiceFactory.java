package com.phonebook.contacts_app.service;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service

public class ContactServiceFactory {
    private final ApplicationContext applicationContext;

    public ContactServiceFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ContactService getStrategy(String channel) {
        return applicationContext.getBean( channel,ContactService.class);
    }
}

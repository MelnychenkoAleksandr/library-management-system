package com.oleksandr.librarymanagementsystem.controllers;

import com.oleksandr.librarymanagementsystem.models.Observer;
import com.oleksandr.librarymanagementsystem.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserObserverAdapter {

    public User getUser(Observer observer){
        User user = (User) observer;
        return user;
    }
}

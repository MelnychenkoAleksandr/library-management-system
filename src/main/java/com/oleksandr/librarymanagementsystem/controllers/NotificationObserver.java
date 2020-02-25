package com.oleksandr.librarymanagementsystem.controllers;

import com.oleksandr.librarymanagementsystem.models.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class NotificationObserver {

    final List<User> users = new LinkedList<>();

    public void addObserver(User user) {
        users.add(user);
    }

    public void removeObserver(User user) {
        users.remove(user);
    }

    public void notifyUsers(final String message) {
        users.forEach(o -> o.update(message));
    }
}

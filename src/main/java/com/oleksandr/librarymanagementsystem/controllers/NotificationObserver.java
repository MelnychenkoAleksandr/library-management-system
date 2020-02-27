package com.oleksandr.librarymanagementsystem.controllers;

import com.oleksandr.librarymanagementsystem.models.*;
import com.oleksandr.librarymanagementsystem.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class NotificationObserver implements Observable {

    final List<Observer> users = new LinkedList<>();

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserObserverAdapter userObserverAdapter;

    public void addObserver(Observer observer) {
        if(!users.contains(observer)) {
            users.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        users.remove(observer);
    }

    public void notifyUsers(final String message, Book book) {
        Iterable<Notification> listAll = notificationRepository.findAll();
        Iterable<Notification> notifications = notificationRepository.findByBookId(book.getId());
        notifications.forEach(notification -> {
            users.forEach(user -> {
                if (notification.getUserId().equals(userObserverAdapter.getUser(user).getId())) {
                    user.update(message);
                    notificationRepository.delete(notification);
                }
            });
        });
    }
}

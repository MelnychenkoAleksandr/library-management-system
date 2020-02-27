package com.oleksandr.librarymanagementsystem.controllers;

import com.oleksandr.librarymanagementsystem.models.Book;
import com.oleksandr.librarymanagementsystem.models.Notification;
import com.oleksandr.librarymanagementsystem.models.Observable;
import com.oleksandr.librarymanagementsystem.models.Observer;
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

    public void addObserver(Observer observer) {
        users.add(observer);
    }

    public void removeObserver(Observer observer) {
        users.remove(observer);
    }

    public void notifyUsers(final String message, Book book) {
        Iterable<Notification> notifications = notificationRepository.findByBook(book);
        notifications.forEach(notification -> {
            users.forEach(user -> {
                if (notification.getUser().equals(user)) {
                    user.update(message);
                    notificationRepository.delete(notification);
                }
            });
        });
    }
}

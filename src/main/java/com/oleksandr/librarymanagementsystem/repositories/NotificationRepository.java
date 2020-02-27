package com.oleksandr.librarymanagementsystem.repositories;

import com.oleksandr.librarymanagementsystem.models.Book;
import com.oleksandr.librarymanagementsystem.models.Notification;
import com.oleksandr.librarymanagementsystem.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    ArrayList<Notification> findByBook(Book book);
    ArrayList<Notification> findByUser(User user);
}

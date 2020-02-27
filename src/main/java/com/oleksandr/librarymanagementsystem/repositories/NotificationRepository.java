package com.oleksandr.librarymanagementsystem.repositories;

import com.oleksandr.librarymanagementsystem.models.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    ArrayList<Notification> findByBookId(Integer bookId);
    ArrayList<Notification> findByUserId(Integer userId);
}

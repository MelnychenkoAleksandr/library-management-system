package com.oleksandr.librarymanagementsystem.models;

public interface Observable {

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyUsers(final String message, Book object);
}

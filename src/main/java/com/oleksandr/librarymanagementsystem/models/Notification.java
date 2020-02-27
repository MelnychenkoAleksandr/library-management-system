package com.oleksandr.librarymanagementsystem.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notification {
    private static final long serialVersionUID = -264567890765432L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "user")
    User user;
    @Column(name = "book")
    Book book;

    public Notification(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return user.equals(that.user) &&
                book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, book);
    }
}

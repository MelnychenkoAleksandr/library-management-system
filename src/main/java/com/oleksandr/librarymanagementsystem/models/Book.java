package com.oleksandr.librarymanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reader"})
public class Book implements Serializable {
    private static final long serialVersionUID = -234567890765432L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "author")
    String author;
    @Column(name = "isbn")
    String isbn;
    @Column(name = "description")
    String description;
    @Column(name = "available")
    boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User reader;

    public Book() {
    }

    public Book(Integer id, String name, String author, String isbn, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.available = true;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                author.equals(book.author) &&
                isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }

    public static final class BookBuilder {

        Integer id;
        String name;
        String author;
        String isbn;
        String description;

        public static BookBuilder aBookBuilder() {
            return new BookBuilder();
        }

        public BookBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BookBuilder withName(String drink) {
            this.name = drink;
            return this;
        }

        public BookBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder withISBN(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(id, name, author, isbn, description);
        }
    }
}

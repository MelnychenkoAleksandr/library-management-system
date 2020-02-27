package com.oleksandr.librarymanagementsystem.webcontrollers;

import com.oleksandr.librarymanagementsystem.models.Book;
import com.oleksandr.librarymanagementsystem.models.User;
import com.oleksandr.librarymanagementsystem.repositories.BookRepository;
import com.oleksandr.librarymanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/addusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUsersToDB() {
        createListOfUsers().forEach(user -> userRepository.save(user));
        return "Users are added.";
    }

    @GetMapping(value = "/addbooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addBooksToDB() {
        buildTestBooks().forEach(book -> bookRepository.save(book));
        return "Books are added.";
    }

    private List<Book> buildTestBooks() {
        List<Book> books = new ArrayList<>();
        books.add(Book.BookBuilder.aBookBuilder()
                .withAuthor("Carlo Collodi.")
                .withDescription("Story about the wooden boy and his father..")
                .withISBN("isbn1")
                .withName("Pinocchio")
                .build());
        books.add(Book.BookBuilder.aBookBuilder()
                .withAuthor("Miguel de Cervantes.")
                .withDescription("About retired country gentleman in his fifties.")
                .withISBN("isbn1")
                .withName("Don Quixote")
                .build());
        books.add(Book.BookBuilder.aBookBuilder()
                .withAuthor("W. Sheakspire.")
                .withDescription("Ancient poems.")
                .withISBN("isbn1")
                .withName("Poems")
                .build());
        return books;
    }

    private List<User> createListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alf", "Tunner", "America", "Melmac phoone", "reader", new ArrayList<Book>()));
        users.add(new User(2, "Thor", "The God", "Asgard", "No", "the god", new ArrayList<Book>()));
        return users;
    }
}

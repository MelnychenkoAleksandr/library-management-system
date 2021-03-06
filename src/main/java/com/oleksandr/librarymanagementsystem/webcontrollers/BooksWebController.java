package com.oleksandr.librarymanagementsystem.webcontrollers;

import com.oleksandr.librarymanagementsystem.models.BlackCopier;
import com.oleksandr.librarymanagementsystem.models.Book;
import com.oleksandr.librarymanagementsystem.models.ColorCopier;
import com.oleksandr.librarymanagementsystem.models.Copier;
import com.oleksandr.librarymanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BooksWebController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/{bookid}.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable("bookid") String bookId) {
        return bookRepository.findById(Integer.parseInt(bookId)).get();
    }

    @GetMapping(value = "/getall")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping(value = "/addbook")
    public String showSignUpForm(Book book) {
        return "addBook";
    }

    @PostMapping(value = "/addbook")
    public String addBook(@Valid Book book, Model model) {
        if (isBookExists(book)) {
            return "The book is already exists.";
        }
        book.setAvailable(true);
        bookRepository.save(book);
        return "redirect:/book/getall";
    }

    @GetMapping(value = "/copybook")
    public String copyBook(@RequestParam String bookId, @RequestParam String color) {
        Copier copier;
        if ("color".equals(color)) {
            copier = new ColorCopier();
        } else {
            copier = new BlackCopier();
        }
        Book book = bookRepository.findById(Integer.parseInt(bookId)).get();
        copier.copy(book);
        return "redirect:/book/getall";
    }

    private boolean isBookExists(Book newBook) {
        Iterable<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (newBook.equals(book)) return true;
        }
        return false;
    }
}

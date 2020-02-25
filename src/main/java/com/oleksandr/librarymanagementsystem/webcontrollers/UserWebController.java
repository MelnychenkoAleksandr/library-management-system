package com.oleksandr.librarymanagementsystem.webcontrollers;

import com.oleksandr.librarymanagementsystem.controllers.NotificationObserver;
import com.oleksandr.librarymanagementsystem.models.Book;
import com.oleksandr.librarymanagementsystem.models.User;
import com.oleksandr.librarymanagementsystem.repositories.BookRepository;
import com.oleksandr.librarymanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NotificationObserver notificationObserver;

    @GetMapping(value = "/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserById(@PathVariable("userid") String userId, Model model) {
        model.addAttribute("books", bookRepository.findByAvailable(true));
        model.addAttribute("user", userRepository.findById(Integer.parseInt(userId)).get());
        return "userPage";
    }

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllUsers(Model model) {
        userRepository.findAll();
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }

    @GetMapping("/addUser")
    public String showSignUpForm(User student) {
        return "addUser";
    }

    @PostMapping(value = "/adduser")
    public String addUser(@Valid User user, Model model) {
        if (isUserExist(user)) {
            return "User already exists.";
        }
        userRepository.save(user);
        return "redirect:/user/getall";
    }

    @GetMapping(value = "/{userId}/takebook")
    public String addBookToUser(@PathVariable(value = "userId") String userId,
                                @RequestParam(required = true) String bookId,
                                Model model) {
        User user = userRepository.findById(Integer.parseInt(userId)).get();
        Book book = bookRepository.findById(Integer.parseInt(bookId)).get();
        if (user == null) return "Can not find user by Id";
        if (book == null) return "Can not find book by Id";
        if (book.isAvailable()) {
            user.getTakenBooks().add(book);
            book.setAvailable(false);
            book.setReader(user);
            model.addAttribute("books", bookRepository.findByAvailable(true));
            model.addAttribute("user", userRepository.findById(Integer.parseInt(userId)).get());
            bookRepository.save(book);
            userRepository.save(user);
            return "userPage";
        }
        return "userPage";
    }

    @GetMapping(value = "/{userId}/notify")
    public String addNotification(@PathVariable(value = "userId") String userId,
                                  @RequestParam(required = true) String bookId,
                                  Model model){

        User user = userRepository.findById(Integer.parseInt(userId)).get();
        notificationObserver.addObserver(user);
        return "userPage";
    }

    @GetMapping(value = "/{userId}/returnBook")
    public String returnBook(@PathVariable(value = "userId") String userId,
                           @RequestParam(required = true) String bookId, Model model) {
        User user = userRepository.findById(Integer.parseInt(userId)).get();
        Book book = bookRepository.findById(Integer.parseInt(bookId)).get();
        if(user==null) return "User does not exist.";
        if(book==null) return "Book can't be found by id";
        if (user != null && book != null) {
            model.addAttribute("userId", user.getId());
            model.addAttribute("bookId", book.getId());
            user.getTakenBooks().add(book);
            book.setAvailable(true);
            book.setReader(null);
            notificationObserver.notifyUsers("The book "+book.getName() + " is returned and you can take it.");
            bookRepository.save(book);
            userRepository.save(user);
            return "redirect:/user/"+userId;
        }
        return "redirect:/user/"+userId;
    }

    private boolean isUserExist(User newUser) {
        Iterable<User> users = userRepository.findAll();
        ArrayList<User> userList = new ArrayList<>();
        users.forEach(b -> userList.add(b));
        for (User user : userList) {
            if (user.equals(newUser)) return true;
        }
        return false;
    }
}

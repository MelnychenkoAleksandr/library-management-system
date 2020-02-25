package com.oleksandr.librarymanagementsystem.repositories;

import com.oleksandr.librarymanagementsystem.models.Book;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {
    ArrayList<Book> findByAvailable(boolean available)
            ;
}

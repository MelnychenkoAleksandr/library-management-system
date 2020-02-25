package com.oleksandr.librarymanagementsystem.repositories;


import com.oleksandr.librarymanagementsystem.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
}


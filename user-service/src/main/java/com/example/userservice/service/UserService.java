package com.example.userservice.service;

import java.util.List;

import com.example.userservice.model.User;


public interface UserService {
    
    List<User> findAll();

    User findById(Long id);

    User save(User User);

    User update(User user);

    void delete(Long id);

}

package com.example.mylibrary.manager;

import com.example.mylibrary.model.User;

import java.util.List;

public interface UserManager {
    void saveUser(User user);

    User getByEmailAndPassword(String email, String password);

    User getByEmail(String email);

    List<User> getAllByUser();
    User getById(int id);
}

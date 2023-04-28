package com.example.mylibrary.manager;

import com.example.mylibrary.model.User;

public interface UserManager {
    void saveUser(User user);

    User getByEmailAndPassword(String email, String password);

    User getByEmail(String email);
}

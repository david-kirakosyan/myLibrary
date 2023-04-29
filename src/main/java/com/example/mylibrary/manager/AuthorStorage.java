package com.example.mylibrary.manager;

import com.example.mylibrary.model.Author;

import java.util.List;

public interface AuthorStorage {
    void saveAuthor(Author author);

    List<Author> getAllByAuthor();

    Author getById(int id);
    List<Author> getAuthors(String name);

    Author getByEmail(String email);
    void editAuthor(Author author);

    void removeById(int id);
}

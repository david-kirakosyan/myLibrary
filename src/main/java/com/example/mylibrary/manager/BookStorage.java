package com.example.mylibrary.manager;

import com.example.mylibrary.model.Book;

import java.util.List;

public interface BookStorage {
    void saveBook(Book book);

    List<Book> getAllByBook();

    Book getById(int id);

    List<Book> search(String keyword);

    List<Book> getAllByUserBook(int user_id);

    void editBook(Book book);

    void removeById(int id);
}

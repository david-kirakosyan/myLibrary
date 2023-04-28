package com.example.mylibrary.servlet.book;


import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.manager.impl.BookStorageImpl;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();
    private BookStorage bookStorage = new BookStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Author> author = authorStorage.getAllByAuthor();
        Book book = bookStorage.getById(id);
        req.setAttribute("author", author);
        req.setAttribute("books", book);
        req.getRequestDispatcher("WEB-INF/editBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookStorage.editBook(Book.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Double.parseDouble(req.getParameter("price")))
                .author(authorStorage.getById(Integer.parseInt(req.getParameter("author_id"))))
                .build());
        resp.sendRedirect("/books");
    }
}

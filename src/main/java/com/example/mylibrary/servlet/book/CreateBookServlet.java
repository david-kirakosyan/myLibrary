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

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();
    private BookStorage bookStorage = new BookStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> author = authorStorage.getAllByAuthor();
        req.setAttribute("author", author);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        bookStorage.saveBook(Book.builder()
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Double.parseDouble(req.getParameter("price")))
                .author(authorStorage.getById(Integer.parseInt(req.getParameter("author_id"))))
                .build());
        resp.sendRedirect("/books");

    }
}

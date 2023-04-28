package com.example.mylibrary.servlet.book;


import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.impl.BookStorageImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {

    private BookStorage bookStorage = new BookStorageImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookStorage.removeById(id);
        resp.sendRedirect("/books");
    }
}

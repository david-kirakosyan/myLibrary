package com.example.mylibrary.servlet.author;


import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeAuthor")
public class RemoveAuthorsServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        authorStorage.removeById(id);
        resp.sendRedirect("/authors");
    }
}

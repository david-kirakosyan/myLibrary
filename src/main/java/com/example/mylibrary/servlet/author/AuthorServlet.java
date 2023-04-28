package com.example.mylibrary.servlet.author;


import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/authors")
public class AuthorServlet extends HttpServlet {


    private AuthorStorage authorStorage = new AuthorStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<Author> authors = authorStorage.getAuthors(name);
        req.setAttribute("authors",authors);
        req.getRequestDispatcher("WEB-INF/authors.jsp").forward(req,resp);
    }
}

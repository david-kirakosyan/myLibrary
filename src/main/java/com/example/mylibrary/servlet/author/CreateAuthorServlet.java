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

@WebServlet("/createAuthor")
public class CreateAuthorServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/createAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        authorStorage.saveAuthor(Author.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .age(Integer.parseInt(req.getParameter("age")))
                .build());
        resp.sendRedirect("/authors");

    }
}

package com.example.mylibrary.servlet.author;

import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editAuthor")
public class EditAuthorServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author author = authorStorage.getById(id);
        req.setAttribute("author", author);
        req.getRequestDispatcher("WEB-INF/editAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Author author = authorStorage.getByEmail(email);
        if (author == null) {
            authorStorage.editAuthor(Author.builder()
                    .id(Integer.parseInt(req.getParameter("id")))
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .age(Integer.parseInt(req.getParameter("age")))
                    .build());
        }
            resp.sendRedirect("/authors");
        }
    }


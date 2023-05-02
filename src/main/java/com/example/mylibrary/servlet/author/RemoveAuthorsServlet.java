package com.example.mylibrary.servlet.author;


import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/removeAuthor")
public class RemoveAuthorsServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author byId = authorStorage.getById(id);
        if (byId != null) {
            if (byId.getPicName() == null ) {
                File file = new File(SharedConstants.UPLOAD_FOLDER_BOOK + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            } else if (byId.getPicName() != null || byId.getPicName().equalsIgnoreCase("null")) {
                File file = new File(SharedConstants.UPLOAD_FOLDER_AUTHOR + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        authorStorage.removeById(id);
        resp.sendRedirect("/authors");
    }
}

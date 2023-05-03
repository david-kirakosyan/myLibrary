package com.example.mylibrary.servlet.book;


import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.impl.BookStorageImpl;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {

    private BookStorage bookStorage = new BookStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Book byId = bookStorage.getById(id);
        if (byId != null) {
            File file = new File(SharedConstants.UPLOAD_FOLDER_BOOK + byId.getImage());
            if (byId.getImage() == null ) {
                if (file.exists()) {
                    file.delete();
                }
            } else if (byId.getImage() != null || byId.getImage().equalsIgnoreCase("null")) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        bookStorage.removeById(id);
        resp.sendRedirect("/books");
    }
}

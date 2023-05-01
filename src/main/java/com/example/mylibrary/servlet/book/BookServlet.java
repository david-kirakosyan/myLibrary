package com.example.mylibrary.servlet.book;


import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.impl.BookStorageImpl;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookStorage bookStorage = new BookStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String keyword = req.getParameter("keyword");

        List<Book> book = null;
        if (keyword == null || keyword.equals("")){
            book = bookStorage.getAllByBook();
        } else {
            book = bookStorage.search(keyword);
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req, resp);
    }
}

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

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {

    private BookStorage bookStorage = new BookStorageImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        List<Book> byTitle = bookStorage.getBooks(title);
        req.setAttribute("title", byTitle);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req, resp);
    }
}

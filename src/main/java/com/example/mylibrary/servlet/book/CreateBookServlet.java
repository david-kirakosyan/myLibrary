package com.example.mylibrary.servlet.book;


import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.manager.impl.BookStorageImpl;
import com.example.mylibrary.manager.impl.UserManagerImpl;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class CreateBookServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();
    private BookStorage bookStorage = new BookStorageImpl();

    private UserManager userManager = new UserManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> author = authorStorage.getAllByAuthor();
        List<User> user = userManager.getAllByUser();
        req.setAttribute("author", author);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        Part profilePicPart = req.getPart("profilePic");
        String picName = null;
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(SharedConstants.UPLOAD_FOLDER_BOOK + picName);
        }
                bookStorage.saveBook(Book.builder()
                        .title(req.getParameter("title"))
                        .description(req.getParameter("description"))
                        .price(Double.parseDouble(req.getParameter("price")))
                        .author(authorStorage.getById(Integer.parseInt(req.getParameter("author_id"))))
                        .user(userManager.getById(user.getId()))
                        .image(picName)
                        .build());

        resp.sendRedirect("/users");

    }
}

package com.example.mylibrary.servlet.author;


import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.impl.AuthorStorageImpl;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/createAuthor")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class CreateAuthorServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> author = authorStorage.getAllByAuthor();
        req.setAttribute("authors", author);
        req.getRequestDispatcher("WEB-INF/createAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));

        Author author = authorStorage.getByEmail(email);

        Part profilePicPart = req.getPart("profilePic");
        String picName = null;
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(SharedConstants.UPLOAD_FOLDER_AUTHOR + picName);
        }
        String msg = "";
        if (name == null || name.trim().equals("")) {
            msg += "Name is required<br>";
        }
        if (surname == null || surname.trim().equals("")) {
            msg += "Surname is required<br>";
        }
        if (age <= 10) {
            msg += "Age is less than 10 <br>";
        }
        if (email == null || email.trim().equals("")) {
            msg += "Email is required<br>";
        } else if (!EmailUtil.patternMatches(email)) {
            msg += "Email format is incorrect<br>";
        }
        if (msg.equals("")) {
            if (author == null) {
                authorStorage.saveAuthor(Author.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .picName(picName)
                        .build());
            }
            resp.sendRedirect("/authors");
        } else {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/WEB-INF/createAuthor.jsp").forward(req, resp);
        }
    }
}

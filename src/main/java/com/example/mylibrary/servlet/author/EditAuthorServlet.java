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
import java.io.File;
import java.io.IOException;

@WebServlet("/editAuthor")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class EditAuthorServlet extends HttpServlet {

    private AuthorStorage authorStorage = new AuthorStorageImpl();

    private static final File UPLOAD_AUTHOR = new File(SharedConstants.UPLOAD_FOLDER_AUTHOR);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author author = authorStorage.getById(id);
        req.setAttribute("author", author);
        req.getRequestDispatcher("WEB-INF/editAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author byId = authorStorage.getById(id);
        if (byId != null) {
            File file = new File(SharedConstants.UPLOAD_FOLDER_AUTHOR + byId.getPicName());
            if (byId.getPicName() == null) {
                if (file.exists()) {
                    file.delete();
                }
            } else if (byId.getPicName() != null || byId.getPicName().equalsIgnoreCase("null")) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
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
            msg += "Age is less than 10<br>";
        }
        if (email == null || email.trim().equals("")) {
            msg += "Email is required<br>";
        } else if (!EmailUtil.patternMatches(email)) {
            msg += "Email format is incorrect<br>";
        }
        if (msg.equals("")) {
            authorStorage.editAuthor(Author.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .picName(picName)
                    .build());
            resp.sendRedirect("/authors");
        }else {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/WEB-INF/editAuthor.jsp").forward(req, resp);
        }
    }
}


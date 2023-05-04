package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.manager.impl.UserManagerImpl;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;
import com.example.mylibrary.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManagerImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserType type = UserType.valueOf(req.getParameter("type"));
        User user = userManager.getByEmail(email);
        String msg = "";
        if (name == null || name.trim().equals("")) {
            msg += "Name is required<br>";
        }
        if (surname == null || surname.trim().equals("")) {
            msg += "Surname is required<br>";
        }
        if (email == null || email.trim().equals("")) {
            msg += "Email is required<br>";
        } else if (!EmailUtil.patternMatches(email)) {
            msg += "Email format is incorrect<br>";
        }
        if (password == null || password.trim().equals("")) {
            msg += "Password is required<br>";
        } else if (password.length() < 6) {
            msg += "password is less than 6 <br>";
        }
        if (msg.equals("")) {

            if (user == null) {
                userManager.saveUser(User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .userType(type)
                        .build());

            }
            resp.sendRedirect("/");
        } else {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}


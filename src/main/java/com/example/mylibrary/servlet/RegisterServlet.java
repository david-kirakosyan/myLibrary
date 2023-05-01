package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.manager.impl.UserManagerImpl;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;

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
        String email = req.getParameter("email");
        User user = userManager.getByEmail(email);
        if (user == null){
            userManager.saveUser(User.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .password(req.getParameter("password"))
                            .userType(UserType.valueOf(req.getParameter("type")))
                    .build());

        }
        resp.sendRedirect("/");
    }
}


package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.manager.impl.UserManagerImpl;
import com.example.mylibrary.model.User;
import com.example.mylibrary.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManagerImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String msg ="";
        if (email == null || email.trim().equals("")) {
            msg += "Email and Password is required<br>";
        } else if (!EmailUtil.patternMatches(email)) {
            msg += "Email and Password format is incorrect <br>";
        }
        if (password == null || password.trim().equals("")) {
            msg += "Email and Password is required<br>";
        } else if (password.length() < 4) {
            msg += "Email and Password format is incorrect <br>";
        }
        if(msg.equals("")) {
            User user = userManager.getByEmailAndPassword(email, password);
            HttpSession session = req.getSession();
            if (user != null) {
                session.setAttribute("user", user);
                resp.sendRedirect("/home");
            } else {
                session.setAttribute("msg", "Username or Password is incorrect");
                resp.sendRedirect("/");
            }
        }else {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

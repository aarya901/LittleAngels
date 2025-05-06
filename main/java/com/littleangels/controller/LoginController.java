package com.littleangels.controller;

import com.littleangels.model.UserModel;
import com.littleangels.service.LoginService;
import com.littleangels.util.CookieUtil;
import com.littleangels.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        String role = request.getParameter("user_role");

        // Create user model
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(password);
        userModel.setRole(role);

        // Call login service
        LoginService loginService = new LoginService();
        Boolean isValid = loginService.loginUser(userModel);

        if (isValid != null && isValid) {
            // Set session and cookie BEFORE forwarding/redirecting
            SessionUtil.setAttribute(request, "username", username);
            CookieUtil.addCookie(response, "role", role, 60 * 60); // 1 hour

            if ("admin".equalsIgnoreCase(role)) {
                SessionUtil.setAttribute(request, "admin", username); // <-- Add this line
                response.sendRedirect(request.getContextPath() + "/adminn");
            } else if ("customer".equalsIgnoreCase(role)) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("error", "Invalid role selection.");
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            }
        } else if (isValid == null) {
            request.setAttribute("error", "Database connection error.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Wrong username, role or password.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}

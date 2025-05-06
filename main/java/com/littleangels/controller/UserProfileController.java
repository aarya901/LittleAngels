package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/user-profile")
public class UserProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve the session username attribute
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // If username is not in session, redirect to login page
        if (username == null) {
            response.sendRedirect("login");
            return;
        }

        // Query the database to fetch the user profile data
        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT * FROM user WHERE user_name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                // If user is found, set the user details and forward to the profile page
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setUserName(rs.getString("user_name"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("user_email"));  // Use user_email column here
                    user.setPhone(rs.getString("user_phone"));
                    user.setAddress(rs.getString("address"));
                    user.setImagePath(rs.getString("user_image"));

                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
                } else {
                    // If user is not found, redirect to login page
                    response.sendRedirect("login");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Could not load profile.");
            request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
        }
    }
}

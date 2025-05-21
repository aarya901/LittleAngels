package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Handles admin profile and updates the user data into the database
 * 
 * @author Aarya Gautam
 */

@WebServlet("/admin-profile")
public class AdminProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Loads the user profile data from the database and forwards to profile JSP.
	 * Redirects to login page if no user session exists.
	 * 
	 * @param request  HttpServletRequest with session attribute "username"
	 * @param response HttpServletResponse for forwarding or redirecting
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (username == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "SELECT * FROM user WHERE user_name = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					UserModel user = new UserModel();
					user.setUserName(rs.getString("user_name"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setEmail(rs.getString("user_email"));
					user.setPhone(rs.getString("user_phone"));
					user.setAddress(rs.getString("address"));
					user.setImagePath(rs.getString("user_image"));
					user.setGender(rs.getString("gender"));
					user.setDob(rs.getDate("dob"));

					request.setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/pages/admin-profile.jsp").forward(request, response);
				} else {
					response.sendRedirect("login.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Could not load profile.");
			request.getRequestDispatcher("/WEB-INF/pages/admin-profile.jsp").forward(request, response);
		}
	}

	/**
	 * Updates the user profile in the database with submitted form data. If session
	 * is missing, redirects to login page. On success or failure, reloads profile
	 * page with message.
	 * 
	 * @param request  HttpServletRequest containing updated profile form data and
	 *                 session "username"
	 * @param response HttpServletResponse for forwarding or redirecting
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (username == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String dobStr = request.getParameter("dob");

		try {
			java.util.Date parsedDob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
			java.sql.Date dob = new java.sql.Date(parsedDob.getTime());

			try (Connection conn = DbConfig.getDbConnection()) {
				String sql = "UPDATE user SET first_name=?, last_name=?, user_email=?, user_phone=?, address=?, gender=?, dob=? WHERE user_name=?";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, firstName);
					stmt.setString(2, lastName);
					stmt.setString(3, email);
					stmt.setString(4, phone);
					stmt.setString(5, address);
					stmt.setString(6, gender);
					stmt.setDate(7, dob);
					stmt.setString(8, username);

					int updated = stmt.executeUpdate();

					if (updated > 0) {
						request.setAttribute("message", "Profile updated successfully.");
					} else {
						request.setAttribute("error", "Update failed.");
					}

					doGet(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "An error occurred while updating profile.");
			doGet(request, response);
		}
	}
}

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
 * Servlet for handling user profile view and update operations.
 * 
 * @author Priya Soni
 */
@WebServlet("/user-profile")
public class UserProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests to display the user profile page.
	 *
	 * @param request  the HttpServletRequest object that contains the request the
	 *                 client has made
	 * @param response the HttpServletResponse object that contains the response the
	 *                 servlet sends to the client
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		// Redirect to login if not logged in
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
					// Populate UserModel with user data
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

					// Set user as a request attribute and forward to JSP
					request.setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
				} else {
					response.sendRedirect("login.jsp");
				}
			}
		} catch (Exception e) {
			// Handle any error and forward with error message
			e.printStackTrace();
			request.setAttribute("error", "Could not load profile.");
			request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
		}
	}

	/**
	 * Handles POST requests to update user profile information.
	 *
	 * @param request  the HttpServletRequest object that contains the form data
	 * @param response the HttpServletResponse object that contains the servlet's
	 *                 response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		// Redirect to login if not logged in
		if (username == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Get form parameters
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String dobStr = request.getParameter("dob");

		try {
			// Convert date string to java.sql.Date
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

					// Show the updated profile
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

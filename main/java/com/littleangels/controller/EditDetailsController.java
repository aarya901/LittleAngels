package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Redirects to edit details after successful update of user information and
 * updates it in the database author Aarya Gautam
 */
@WebServlet("/edit_details")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB before file is written to disk
		maxFileSize = 5 * 1024 * 1024, // max 5MB per file
		maxRequestSize = 10 * 1024 * 1024 // max 10MB for entire request
)
public class EditDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET request to load current user details for editing. Loads user data
	 * from DB using username stored in session. Forwards to edit_details.jsp with
	 * user data set as attribute. Redirects to login.jsp if user not logged in.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException on servlet errors
	 * @throws IOException      on I/O errors
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
					user.setGender(rs.getString("gender"));
					user.setDob(rs.getDate("dob"));
					user.setImagePath(rs.getString("user_image"));

					request.setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/pages/edit_details.jsp").forward(request, response);
				} else {
					response.sendRedirect("login.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Unable to load profile.");
			request.getRequestDispatcher("/WEB-INF/pages/edit_details.jsp").forward(request, response);
		}
	}

	/**
	 * Handles POST request to update user profile data. Updates user data in DB and
	 * reloads edit form with status message. Redirects to login.jsp if user not
	 * logged in.
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException on servlet errors
	 * @throws IOException      on I/O errors
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
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

		// Handle uploaded image file
		Part imagePart = request.getPart("image"); // <input type="file" name="image">
		String imageFileName = null;

		if (imagePart != null && imagePart.getSize() > 0) {
			// Extract original file name
			String submittedFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

			// Generate unique file name to avoid conflicts
			String extension = "";
			int i = submittedFileName.lastIndexOf('.');
			if (i >= 0) {
				extension = submittedFileName.substring(i);
			}
			imageFileName = UUID.randomUUID().toString() + extension;

			// Define upload directory (make sure this directory exists and is writable)
			String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";

			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}

			// Save file on server
			try (InputStream input = imagePart.getInputStream()) {
				Path filePath = Paths.get(uploadDir, imageFileName);
				Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("error", "Failed to upload image.");
				request.getRequestDispatcher("/WEB-INF/pages/edit_details.jsp").forward(request, response);
				return;
			}
		}

		try (Connection conn = DbConfig.getDbConnection()) {
			// If image uploaded, include user_image column in update
			String sql;
			if (imageFileName != null) {
				sql = "UPDATE user SET first_name = ?, last_name = ?, user_email = ?, user_phone = ?, address = ?, gender = ?, dob = ?, user_image = ? WHERE user_name = ?";
			} else {
				sql = "UPDATE user SET first_name = ?, last_name = ?, user_email = ?, user_phone = ?, address = ?, gender = ?, dob = ? WHERE user_name = ?";
			}

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, firstName);
				stmt.setString(2, lastName);
				stmt.setString(3, email);
				stmt.setString(4, phone);
				stmt.setString(5, address);
				stmt.setString(6, gender);

				java.sql.Date dob = null;
				if (dobStr != null && !dobStr.isEmpty()) {
					java.util.Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
					dob = new java.sql.Date(parsedDate.getTime());
				}
				stmt.setDate(7, dob);

				if (imageFileName != null) {
					stmt.setString(8, "uploads/" + imageFileName);
					stmt.setString(9, username);
				} else {
					stmt.setString(8, username);
				}

				int updated = stmt.executeUpdate();

				if (updated > 0) {
					request.setAttribute("message", "Profile updated successfully.");
				} else {
					request.setAttribute("error", "No changes made.");
				}

				UserModel user = new UserModel();
				user.setUserName(username);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setPhone(phone);
				user.setAddress(address);
				user.setGender(gender);
				user.setDob(dob);

				// Set updated image path in UserModel if image uploaded
				if (imageFileName != null) {
					user.setImagePath("uploads/" + imageFileName);
				} else {
					// If no new image uploaded, try to get current image path from DB to keep it
					// consistent
					try (PreparedStatement ps = conn
							.prepareStatement("SELECT user_image FROM user WHERE user_name = ?")) {
						ps.setString(1, username);
						ResultSet rs = ps.executeQuery();
						if (rs.next()) {
							user.setImagePath(rs.getString("user_image"));
						}
					} catch (Exception e) {
						// ignore, just leave imagePath null
					}
				}

				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/pages/edit_details.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to update profile.");
			request.getRequestDispatcher("/WEB-INF/pages/edit_details.jsp").forward(request, response);
		}
	}
}

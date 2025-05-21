package com.littleangels.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.littleangels.model.UserModel;
import com.littleangels.service.RegisterService;
import com.littleangels.util.ValidationUtil;
import com.littleangels.util.ImageUtil;
import com.littleangels.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Controller for handling user registration. Validates input, encrypts
 * passwords, uploads profile images, and persists user data via
 * RegisterService.
 * 
 * Author: Aarya Gautam
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final RegisterService registerService = new RegisterService();
	private final ImageUtil imageUtil = new ImageUtil();

	/**
	 * Loads the registration form JSP.
	 * 
	 * @param req  the HttpServletRequest
	 * @param resp the HttpServletResponse
	 * 
	 * @throws ServletException if forwarding fails
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}

	/**
	 * Handles form submission for user registration. Validates inputs, processes
	 * image, encrypts password, and stores user info.
	 * 
	 * @param req  the HttpServletRequest
	 * @param resp the HttpServletResponse
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			UserModel userModel = extractUserModel(req);

			Part imagePart = req.getPart("user_image");
			if (!ValidationUtil.isValidImageExtension(imagePart)) {
				handleError(req, resp, "Invalid image format. Please upload a jpg, jpeg, png, or gif image.");
				return;
			}

			String errorMessage = registerService.addUser(userModel);

			if (errorMessage == null) {
				handleSuccess(req, resp, "Your account has been successfully created!", "/WEB-INF/pages/login.jsp");
			} else {
				handleError(req, resp, errorMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			handleError(req, resp, "An unexpected error occurred. Please try again later.");
		}
	}

	/**
	 * Validates user input from the registration form.
	 * 
	 * @param req the HttpServletRequest containing user input
	 * 
	 * @return error message if validation fails, or null if valid
	 */
	private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("user_fname");
		String lastName = req.getParameter("user_lname");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String number = req.getParameter("phone");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("retypePassword");
		String address = req.getParameter("address");
		String role = req.getParameter("user_role");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");

		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (!ValidationUtil.isValidEmail(email))
			return "Please enter a valid email address.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must start with '98' and be 10 digits.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";
		if (ValidationUtil.isNullOrEmpty(address))
			return "Address is required.";
		if (ValidationUtil.isNullOrEmpty(role))
			return "Role is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of Birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must have 1 symbol, 1 capital letter, one digit, and be at least 8 characters long.";

		try {
			LocalDate dob = java.sql.Date.valueOf(dobStr).toLocalDate();
			if (!ValidationUtil.isAgeAtLeast16(dob))
				return "You must be at least 16 years old.";
		} catch (Exception e) {
			return "Invalid date format for Date of Birth.";
		}

		return null;
	}

	/**
	 * Extracts and constructs a UserModel from the request parameters.
	 * 
	 * Handles password encryption and image upload.
	 * 
	 * @param req the HttpServletRequest with form data
	 * 
	 * @return UserModel containing parsed and validated user data
	 * @throws IOException      if image read fails
	 * @throws ServletException if part access fails
	 */
	private UserModel extractUserModel(HttpServletRequest req) throws IOException, ServletException {
		String firstName = req.getParameter("user_fname");
		String lastName = req.getParameter("user_lname");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String role = req.getParameter("user_role");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");

		password = PasswordUtil.encrypt(username, password);

		Date dob = null;
		try {
			dob = java.sql.Date.valueOf(dobStr);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid date format for DOB");
		}

		Part imagePart = req.getPart("user_image");
		String imageFileName = imageUtil.getImageNameFromPart(imagePart);

		boolean isUploaded = imageUtil.uploadImage(imagePart);
		if (!isUploaded) {
			System.out.println("Image upload failed.");
		}

		String imageDbPath = "uploads/" + imageFileName;
		System.out.println(imageDbPath);

		return new UserModel(0, firstName, lastName, username, password, phone, email, address, role, dob, imageDbPath,
				gender);
	}

	/**
	 * Forwards the request with a success message.
	 * 
	 * @param req          HttpServletRequest
	 * @param resp         HttpServletResponse
	 * @param message      success message to show
	 * @param redirectPage the JSP page
	 * 
	 * @throws ServletException if forwarding fails
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	/**
	 * Forwards the request with an error message and repopulates user inputs.
	 * 
	 * @param req     HttpServletRequest
	 * @param resp    HttpServletResponse
	 * @param message error message to display
	 * 
	 * @throws ServletException if forwarding fails
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("user_fname", req.getParameter("user_fname"));
		req.setAttribute("user_lname", req.getParameter("user_lname"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("phone", req.getParameter("phone"));
		req.setAttribute("address", req.getParameter("address"));
		req.setAttribute("role", req.getParameter("user_role"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}
}

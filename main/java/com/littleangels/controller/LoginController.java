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

/**
 * Handles login page with successful redirection and validation
 * 
 * @author Rachina Gosai
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests by forwarding to login.jsp
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException on servlet errors
	 * @throws IOException      on I/O errors
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for user login
	 * 
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException on servlet errors
	 * @throws IOException      on I/O errors
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("user_name");
		String password = request.getParameter("user_password");

		// Check for blank inputs
		if (username == null || username.trim().isEmpty()) {
			request.setAttribute("error", "Username cannot be blank.");
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}

		if (password == null || password.trim().isEmpty()) {
			request.setAttribute("error", "Password cannot be blank.");
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}

		UserModel userModel = new UserModel();
		userModel.setUserName(username);
		userModel.setPassword(password);

		LoginService loginService = new LoginService();
		UserModel loggedInUser = null;

		try {
			loggedInUser = loginService.loginUser(userModel);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Database connection error. Please try again later.");
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			return;
		}

		if (loggedInUser != null) {
			String roleValue = loggedInUser.getRole();
			if (roleValue == null) {
				roleValue = "";
			} else {
				roleValue = roleValue.trim().toLowerCase();
			}

			CookieUtil.addCookie(response, "role", roleValue, 60 * 60);
			request.getSession().setAttribute("userId", loggedInUser.getUserId());
			SessionUtil.setAttribute(request, "username", loggedInUser.getUserName());

			if ("admin".equalsIgnoreCase(roleValue)) {
				SessionUtil.setAttribute(request, "admin", loggedInUser.getUserName());
				response.sendRedirect(request.getContextPath() + "/adminn");
			} else {
				SessionUtil.setAttribute(request, "customer", loggedInUser.getUserName());
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} else {
			request.setAttribute("error", "Invalid username or password.");
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
	}

}

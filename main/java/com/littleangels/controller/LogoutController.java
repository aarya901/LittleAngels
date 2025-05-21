package com.littleangels.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.littleangels.util.CookieUtil;
import com.littleangels.util.SessionUtil;

/**
 * Handles user logout by clearing cookies and invalidating the session, then
 * redirects to home page.
 * 
 * Authors: Rachina Gosai
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles POST request to log the user out. Deletes relevant cookies and
	 * invalidates the session. Redirects user to the home page.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CookieUtil.deleteCookie(response, "role");
		CookieUtil.deleteCookie(response, "username");
		SessionUtil.invalidateSession(request);
		response.sendRedirect(request.getContextPath() + "/");
	}

}

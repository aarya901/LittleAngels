package com.littleangels.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Utility class for handling page redirection using request dispatching. It
 * maps page keywords to internal JSP page paths and forwards the request.
 * 
 * Author: Priya Soni
 */
public class RedirectUtil {

	/**
	 * Redirects the request to a specific JSP page based on the input keyword.
	 * Valid options include "about", "contact". If an invalid page is provided, the
	 * user is forwarded to a 404 error page.
	 *
	 * @param request  The HttpServletRequest object from the servlet.
	 * @param response The HttpServletResponse object from the servlet.
	 * @param page     A string keyword representing the target page (e.g., "about",
	 *                 "contact").
	 * @throws ServletException If an error occurs during forwarding.
	 * @throws IOException      If an input or output error is detected.
	 */
	public static void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {

		String target;

		// Determine which JSP page to forward to based on the given keyword
		switch (page) {
		case "about":
			target = "/WEB-INF/pages/about.jsp";
			break;
		case "contact":
			target = "/WEB-INF/pages/contact.jsp";
			break;
		default:
			// If no matching page is found, redirect to 404 page
			target = "/WEB-INF/pages/404.jsp";
		}

		// Forward the request to the resolved JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
}

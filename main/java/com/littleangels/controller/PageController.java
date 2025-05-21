package com.littleangels.controller;

import com.littleangels.util.RedirectUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This servlet handles simple static page routing. It maps URLs like /about and
 * /contact to their respective JSP pages.
 * 
 * 
 * /**
 * 
 * @author Aryan Pudasaini
 */
@WebServlet(urlPatterns = { "/about", "/contact" })
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests for /about and /contact pages.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String page = uri.substring(context.length() + 1);

		System.out.println("Routing to: " + page);
		RedirectUtil.redirectToPage(request, response, page);
	}
}

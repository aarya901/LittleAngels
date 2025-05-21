package com.littleangels.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.littleangels.util.SessionUtil;
import com.littleangels.util.CookieUtil;

/**
 * Filter to control access based on authentication and roles. Allows public
 * access to certain pages, restricts admin pages to admin users, restricts user
 * pages to logged-in customers, and redirects unauthorized access attempts.
 * 
 * Author: Priya Soni
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	// Pages accessible without login
	private static final List<String> PUBLIC_PAGES = Arrays.asList("/home", "/about", "/login", "/register", "/footer",
			"/header");

	// Pages only accessible by admins
	private static final List<String> ADMIN_PAGES = Arrays.asList("/admin-product", "/admin-profile", "/adminn");

	// Pages only accessible by logged-in customers
	private static final List<String> USER_PAGES = Arrays.asList("/cart", "/my-order", "/user-profile", "/product",
			"/contact");

	/**
	 * Filters incoming requests to check authentication and authorization. Allows
	 * access to static resources and public pages without login. Redirects to login
	 * page if accessing restricted pages without proper role.
	 *
	 * @param req   ServletRequest
	 * @param res   ServletResponse
	 * @param chain FilterChain to pass request/response to next entity
	 * @throws IOException      if IO errors occur
	 * @throws ServletException if servlet errors occur
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());

		boolean isLoggedIn = SessionUtil.getAttribute(request, "username") != null;

		String role = null;
		if (CookieUtil.getCookie(request, "role") != null) {
			role = CookieUtil.getCookie(request, "role").getValue();
			if (role != null) {
				role = role.trim().toLowerCase();
			}
		}

		// Allow static resources like css, js, images without filtering
		if (path.matches(".*\\.(css|js|png|jpg|jpeg|gif|woff2?|ttf)$")) {
			chain.doFilter(req, res);
			return;
		}

		// Allow public pages without login
		if (PUBLIC_PAGES.contains(path)) {
			chain.doFilter(req, res);
			return;
		}

		// Restrict admin pages to logged-in admins
		if (ADMIN_PAGES.contains(path)) {
			if (isLoggedIn && "admin".equals(role)) {
				chain.doFilter(req, res);
			} else {
				response.sendRedirect(contextPath + "/login");
			}
			return;
		}

		// Restrict user pages to logged-in customers
		if (USER_PAGES.contains(path)) {
			if (isLoggedIn && "customer".equals(role)) {
				chain.doFilter(req, res);
			} else {
				response.sendRedirect(contextPath + "/login");
			}
			return;
		}

		// Redirect unauthenticated users trying to access other pages to home
		if (!isLoggedIn) {
			response.sendRedirect(contextPath + "/home");
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		// No initialization required
	}

	@Override
	public void destroy() {
		// No cleanup required
	}
}

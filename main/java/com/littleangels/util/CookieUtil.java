package com.littleangels.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;

/**
 * Utility class for creating, retrieving, and deleting HTTP cookies across the
 * Little Angels web application.
 * 
 * @author Rachina Gosai
 */
public class CookieUtil {

	/**
	 * Adds a new cookie to the HTTP response.
	 *
	 * @param response The HttpServletResponse to which the cookie will be added.
	 * @param name     The name of the cookie.
	 * @param value    The value to store in the cookie.
	 * @param maxAge   The maximum age of the cookie in seconds. A negative value
	 *                 means the cookie is not stored persistently and will be
	 *                 deleted when the browser closes.
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		// Create a new cookie with the specified name and value
		Cookie cookie = new Cookie(name, value);
		// Set how long the cookie should last (in seconds)
		cookie.setMaxAge(maxAge);
		// Make the cookie available to the entire application path
		cookie.setPath("/");
		// Add the cookie to the response so the client receives it
		response.addCookie(cookie);
	}

	/**
	 * Retrieves a cookie from the HTTP request by name.
	 *
	 * @param request The HttpServletRequest containing the cookies.
	 * @param name    The name of the cookie to retrieve.
	 * @return The Cookie object if found; null otherwise.
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		// Ensure the request contains cookies
		if (request.getCookies() != null) {
			// Stream through cookies to find one matching the given name
			return Arrays.stream(request.getCookies()).filter(cookie -> name.equals(cookie.getName())).findFirst()
					.orElse(null);
		}
		// Return null if no cookies are present
		return null;
	}

	/**
	 * Deletes a cookie by setting its max age to zero.
	 *
	 * @param response The HttpServletResponse to which the deletion cookie will be
	 *                 added.
	 * @param name     The name of the cookie to delete.
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		// Create a cookie with the same name and null value to overwrite the existing
		// one
		Cookie cookie = new Cookie(name, null);
		// Setting max age to zero instructs the browser to delete the cookie
		// immediately
		cookie.setMaxAge(0);
		// Ensure deletion applies to the entire application path
		cookie.setPath("/");
		// Add the cookie to the response to send the delete instruction
		response.addCookie(cookie);
	}
}

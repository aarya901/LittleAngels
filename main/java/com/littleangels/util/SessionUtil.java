package com.littleangels.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for managing HTTP session attributes in a web application. This
 * class provides methods to set, retrieve, remove, and invalidate session data.
 * 
 * @author Rachina Gosai
 */
public class SessionUtil {

	/**
	 * Sets an attribute in the current user's session. If a session does not exist,
	 * a new one is created.
	 *
	 * @param request The HttpServletRequest object.
	 * @param key     The name of the attribute to set.
	 * @param value   The value of the attribute.
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}

	/**
	 * Retrieves an attribute from the current user's session. If the session does
	 * not exist, returns null.
	 *
	 * @param request The HttpServletRequest object.
	 * @param key     The name of the attribute to retrieve.
	 * @return The value of the session attribute, or null if not found or session
	 *         doesn't exist.
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(key);
		}
		return null;
	}

	/**
	 * Removes a specific attribute from the current user's session. If the session
	 * does not exist, nothing happens.
	 *
	 * @param request The HttpServletRequest object.
	 * @param key     The name of the attribute to remove.
	 */
	public static void removeAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(key);
		}
	}

	/**
	 * Invalidates the current user's session. If the session does not exist,
	 * nothing happens.
	 *
	 * @param request The HttpServletRequest object.
	 */
	public static void invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
}

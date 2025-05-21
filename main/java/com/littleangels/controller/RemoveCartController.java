package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Controller to handle the removal of items from the shopping cart. Deletes a
 * cart item from the database based on the provided cart item ID.
 *
 * Author: Rachina Gosai
 */
@WebServlet("/remove-cart-item")
public class RemoveCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles POST requests to delete a cart item by its ID.
	 *
	 * @param req  the HTTP request containing the cart item ID
	 * @param resp the HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cartItemIdStr = req.getParameter("cartItemId");

		// Validate that a cart item ID is provided
		if (cartItemIdStr == null || cartItemIdStr.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cart item ID is required.");
			return;
		}

		int cartItemId;
		try {
			cartItemId = Integer.parseInt(cartItemIdStr);
		} catch (NumberFormatException e) {
			// Invalid format for cart item ID
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid cart item ID.");
			return;
		}

		String deleteQuery = "DELETE FROM cart_items WHERE cart_item_id = ?";

		// Attempt to delete the cart item from the database
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

			ps.setInt(1, cartItemId);
			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				// Deletion successful, redirect to cart page
				resp.sendRedirect(req.getContextPath() + "/cart");
			} else {
				// No item found with the given ID
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Cart item not found.");
			}

		} catch (SQLException | ClassNotFoundException e) {
			// Handle SQL or driver-related errors
			throw new ServletException("Failed to remove cart item.", e);
		}
	}
}

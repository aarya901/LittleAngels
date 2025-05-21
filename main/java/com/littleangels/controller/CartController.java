package com.littleangels.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.littleangels.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Redirects to cart page after successful addition of cart and updates in the
 * database
 * 
 * @author Aarya Gautam
 */

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET request to show all cart items for the logged-in user. Loads cart
	 * items from DB and forwards to cart.jsp for display.
	 * 
	 * @param request  HttpServletRequest containing client request
	 * @param response HttpServletResponse to send response
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "SELECT c.cart_item_id, c.user_id, c.product_id, c.cart_quantity, "
					+ "p.product_name, p.price, p.product_image " + "FROM cart_item c "
					+ "JOIN product p ON c.product_id = p.product_id " + "WHERE c.user_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			var rs = ps.executeQuery();
			java.util.List<com.littleangels.model.CartProductModel> cartItems = new java.util.ArrayList<>();

			while (rs.next()) {
				com.littleangels.model.CartProductModel item = new com.littleangels.model.CartProductModel(
						rs.getInt("cart_item_id"), rs.getInt("user_id"), rs.getInt("product_id"),
						rs.getInt("cart_quantity"), rs.getString("product_name"), rs.getDouble("price"),
						rs.getString("product_image"));
				cartItems.add(item);
			}

			request.setAttribute("cartItems", cartItems);
			request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Database error when loading cart items", e);
		}
	}

	/**
	 * Handles POST request to add or remove items from cart. Requires user login.
	 * 
	 * @param request  HttpServletRequest with parameters: - action=remove and
	 *                 cartItemId to remove - productId and quantity to add
	 * @param response HttpServletResponse for redirects
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String action = request.getParameter("action");
		if ("remove".equals(action)) {
			String cartItemIdStr = request.getParameter("cartItemId");
			if (cartItemIdStr == null || cartItemIdStr.isEmpty()) {
				session.setAttribute("cartError", "Invalid item to remove.");
				response.sendRedirect(request.getContextPath() + "/cart");
				return;
			}

			try {
				int cartItemId = Integer.parseInt(cartItemIdStr);
				try (Connection conn = DbConfig.getDbConnection()) {
					String sql = "DELETE FROM cart_item WHERE cart_item_id = ? AND user_id = ?";
					try (PreparedStatement ps = conn.prepareStatement(sql)) {
						ps.setInt(1, cartItemId);
						ps.setInt(2, userId);
						int rowsAffected = ps.executeUpdate();
						if (rowsAffected == 0) {
							session.setAttribute("cartError", "No such item in your cart.");
						} else {
							session.setAttribute("cartSuccess", "Item removed from cart.");
						}
					}
				}
			} catch (NumberFormatException e) {
				session.setAttribute("cartError", "Invalid item ID format.");
			} catch (Exception e) {
				throw new ServletException("Error removing cart item", e);
			}

			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}

		// Add to cart logic
		String productIdStr = request.getParameter("productId");
		String quantityStr = request.getParameter("quantity");
		int productId, quantity;

		try {
			productId = Integer.parseInt(productIdStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/product?error=invalidInput");
			return;
		}

		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "INSERT INTO cart_item (user_id, product_id, cart_quantity) VALUES (?, ?, ?)";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, userId);
				ps.setInt(2, productId);
				ps.setInt(3, quantity);
				ps.executeUpdate();
			}
			session.setAttribute("cartSuccess", "Item added to cart.");
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (Exception e) {
			throw new ServletException("Database error when adding to cart", e);
		}
	}
}

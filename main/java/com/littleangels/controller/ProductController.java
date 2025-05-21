package com.littleangels.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.littleangels.config.DbConfig;
import com.littleangels.model.ProductModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * This servlet handles the listing of products and user interactions such as
 * "Add to Cart" and "Buy Now". It connects to the database and updates or
 * retrieves the necessary product, cart, or order data.
 * 
 * Author: Anushree Gami
 */
@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests for displaying product listings.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listProducts(request, response);
		} catch (Exception e) {
			throw new ServletException("Error loading products", e);
		}
	}

	/**
	 * Handles POST requests for "Add to Cart" and "Buy Now" actions.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if ("addToCart".equals(action)) {
				addToCart(request, response);
			} else if ("buyNow".equals(action)) {
				buyNow(request, response);
			} else {
				doGet(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * Retrieves all products from the database and forwards them to the product.jsp
	 * page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws Exception if a database error occurs
	 */
	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProductModel> products = new ArrayList<>();
		String query = request.getParameter("query"); // Search input from URL
		String sql;
		PreparedStatement ps;

		try (Connection conn = DbConfig.getDbConnection()) {
			if (query != null && !query.trim().isEmpty()) {
				// Filter products by name using LIKE
				sql = "SELECT product_id, product_name, price, product_image FROM product WHERE product_name LIKE ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + query + "%"); // Wildcard search
			} else {
				// Fetch all products
				sql = "SELECT product_id, product_name, price, product_image FROM product";
				ps = conn.prepareStatement(sql);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ProductModel p = new ProductModel(rs.getInt("product_id"), rs.getString("product_name"),
							rs.getDouble("price"), rs.getString("product_image"));
					products.add(p);
				}
			}
		}

		request.setAttribute("productList", products);
		request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
	}

	/**
	 * Adds a selected product to the user's cart.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws Exception if a database or session error occurs
	 */
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		Integer userId = (session != null) ? (Integer) session.getAttribute("userId") : null;
		if (userId == null) {
			response.sendRedirect("login");
			return;
		}

		int productId = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String sql = "INSERT INTO cart_item (user_id, product_id, cart_quantity) VALUES (?, ?, ?)";

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			ps.executeUpdate();
		}

		response.sendRedirect(request.getContextPath() + "/cart");
	}

	/**
	 * Immediately places an order for the selected product and redirects to the
	 * orders page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws Exception if a transaction or session error occurs
	 */
	private void buyNow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		Integer userId = (session != null) ? (Integer) session.getAttribute("userId") : null;
		if (userId == null) {
			response.sendRedirect("login");
			return;
		}

		int productId = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		try (Connection conn = DbConfig.getDbConnection()) {
			conn.setAutoCommit(false); // transaction start

			// Insert order record
			String insertOrderSql = "INSERT INTO `order` (user_id, order_date) VALUES (?, NOW())";
			int orderId = 0;
			try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				psOrder.setInt(1, userId);
				psOrder.executeUpdate();

				ResultSet rs = psOrder.getGeneratedKeys();
				if (rs.next()) {
					orderId = rs.getInt(1);
				} else {
					throw new Exception("Failed to create order, no ID obtained.");
				}
			}

			// Insert order item
			String insertOrderItemSql = "INSERT INTO order_item (order_id, product_id, order_quantity) VALUES (?, ?, ?)";
			try (PreparedStatement psItem = conn.prepareStatement(insertOrderItemSql)) {
				psItem.setInt(1, orderId);
				psItem.setInt(2, productId);
				psItem.setInt(3, quantity);
				psItem.executeUpdate();
			}

			conn.commit(); // commit transaction
		} catch (Exception e) {
			throw new ServletException("Error processing buy now", e);
		}

		response.sendRedirect(request.getContextPath() + "/my-order");
	}

}

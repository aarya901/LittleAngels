package com.littleangels.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.littleangels.config.DbConfig;
import com.littleangels.model.ProductModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Controller servlet for managing products in admin panel. Supports listing all
 * products, deleting products, and redirecting to the product edit page.
 * 
 * Handles GET requests for listing products and redirecting to edit. Handles
 * POST requests for deleting products.
 * 
 * @author Aarya Gautam
 */
@WebServlet("/admin-product")
public class AdminProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Handles HTTP GET requests. If "action" parameter is "list" or null, lists all
	 * products and forwards to admin-product.jsp. If "action" is "edit", redirects
	 * to AddProductController for editing the specified product. Otherwise,
	 * redirects to the default product list page.
	 * 
	 * @param req  HttpServletRequest containing parameters such as "action" and
	 *             "product_id"
	 * @param resp HttpServletResponse used for forwarding or redirecting
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during request handling
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action == null || "list".equalsIgnoreCase(action)) {
			// List all products
			try (Connection conn = DbConfig.getDbConnection()) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM product");
				List<ProductModel> products = new ArrayList<>();
				while (rs.next()) {
					products.add(new ProductModel(rs.getInt("product_id"), rs.getString("product_name"),
							rs.getDouble("price"), rs.getString("product_image")));
				}
				req.setAttribute("products", products);
				req.getRequestDispatcher("/WEB-INF/pages/admin-product.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			}
		} else if ("edit".equalsIgnoreCase(action)) {
			// Redirect to edit product page
			String idStr = req.getParameter("product_id");
			resp.sendRedirect(req.getContextPath() + "/add-product?product_id=" + idStr);
		} else {
			// Unknown action: redirect to product list
			resp.sendRedirect(req.getContextPath() + "/admin-product");
		}
	}

	/**
	 * Handles HTTP POST requests. Processes product deletion if "action" parameter
	 * is "delete" and "product_id" is provided. Otherwise, delegates to doGet for
	 * other actions.
	 * 
	 * @param req  HttpServletRequest containing parameters such as "action" and
	 *             "product_id"
	 * @param resp HttpServletResponse used for redirecting
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during request handling
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Post run");
		String action = req.getParameter("action");

		if ("delete".equalsIgnoreCase(action)) {
			String idStr = req.getParameter("product_id");
			if (idStr != null && !idStr.isEmpty()) {
				try (Connection conn = DbConfig.getDbConnection()) {
					String sql = "DELETE FROM product WHERE product_id = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(idStr));
					stmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting product");
					System.out.println(e);
					return;
				}
			} else {
				System.out.println(idStr);
			}
			// Redirect to product list after delete
			resp.sendRedirect(req.getContextPath() + "/admin-product");
		} else {
			// Other POST actions fallback to GET handler
			doGet(req, resp);
		}
	}
}

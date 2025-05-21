package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import com.littleangels.model.OrderProductModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles recent order feature in the admin dashboard and redirects to the
 * dashboard
 * 
 * @author Aarya Gautam
 */
@WebServlet("/adminn")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET request to load recent orders for admin dashboard. Fetches the 10
	 * most recent orders with product details. Sets recentOrders attribute and
	 * forwards to adminn.jsp.
	 * 
	 * @param request  HttpServletRequest from client
	 * @param response HttpServletResponse to send response
	 * @throws ServletException if servlet error occurs
	 * @throws IOException      if I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<OrderProductModel> recentOrders = new ArrayList<>();

		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "SELECT o.order_id, o.order_date, oi.order_quantity, p.product_name, p.product_image, p.price "
					+ "FROM `order` o " + "JOIN order_item oi ON o.order_id = oi.order_id "
					+ "JOIN product p ON oi.product_id = p.product_id " + "ORDER BY o.order_date DESC LIMIT 10";

			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					OrderProductModel order = new OrderProductModel();
					order.setOrderId(rs.getInt("order_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setProductName(rs.getString("product_name"));
					order.setOrderQuantity(rs.getInt("order_quantity"));
					order.setProductImage(rs.getString("product_image"));
					order.setPrice(rs.getDouble("price"));
					recentOrders.add(order);
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Error fetching admin orders", e);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		request.setAttribute("recentOrders", recentOrders);
		request.getRequestDispatcher("/WEB-INF/pages/adminn.jsp").forward(request, response);
	}
}

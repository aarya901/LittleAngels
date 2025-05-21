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
 * @author Aarya Gautam
 */
@WebServlet("/my-order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			try (Connection conn = DbConfig.getDbConnection()) {
				conn.setAutoCommit(false);

				String insertOrderSql = "INSERT INTO `order` (user_id, order_date) VALUES (?, NOW())";
				int orderId;
				try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSql,
						Statement.RETURN_GENERATED_KEYS)) {
					psOrder.setInt(1, userId);
					psOrder.executeUpdate();
					ResultSet rs = psOrder.getGeneratedKeys();
					if (rs.next()) {
						orderId = rs.getInt(1);
					} else {
						throw new SQLException("Creating order failed, no ID obtained.");
					}
				}

				String insertItemSql = "INSERT INTO order_items (order_id, product_id, order_quantity) VALUES (?, ?, ?)";
				try (PreparedStatement psItem = conn.prepareStatement(insertItemSql)) {
					psItem.setInt(1, orderId);
					psItem.setInt(2, productId);
					psItem.setInt(3, quantity);
					psItem.executeUpdate();
				}

				conn.commit();

				session.setAttribute("orderSuccess", true);
			}
		} catch (Exception e) {
			throw new ServletException("Error placing order", e);
		}

		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = (Integer) request.getSession().getAttribute("userId");
		List<OrderProductModel> orders = new ArrayList<>();

		try (Connection conn = DbConfig.getDbConnection()) {
			String sql = "SELECT o.order_id, oi.order_quantity, p.product_id, p.product_name, p.price, p.product_image "
					+ "FROM `order` o " + "JOIN order_item oi ON o.order_id = oi.order_id "
					+ "JOIN product p ON oi.product_id = p.product_id " + "WHERE o.user_id = ? "
					+ "ORDER BY o.order_date DESC";

			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					OrderProductModel opm = new OrderProductModel();
					opm.setOrderId(rs.getInt("order_id"));
					opm.setProductId(rs.getInt("product_id"));
					opm.setProductName(rs.getString("product_name"));
					opm.setPrice(rs.getDouble("price"));
					opm.setOrderQuantity(rs.getInt("order_quantity"));
					opm.setProductImage(rs.getString("product_image"));

					orders.add(opm);
				}
			}
		} catch (Exception e) {
			throw new ServletException("Error loading orders", e);
		}

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/WEB-INF/pages/my-order.jsp").forward(request, response);
	}
}

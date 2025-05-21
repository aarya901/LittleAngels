package com.littleangels.controller;

import com.littleangels.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

/**
 * Redirects to order page after successful order and updates in the database
 * 
 * @author Aarya Gautam
 */

@WebServlet("/BuyNow")
public class BuyNowController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles the "Buy Now" action by creating a new order and order item in the database.
    
     * @param request HttpServletRequest containing session and parameters
     * @param response HttpServletResponse for redirecting
     * @throws ServletException if database error occurs
     * @throws IOException if I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try (Connection conn = DbConfig.getDbConnection()) {
            conn.setAutoCommit(false); // start transaction

            // Insert new order and get generated order ID
            String insertOrderSql = "INSERT INTO `order` (user_id, order_date) VALUES (?, ?)";
            try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, userId);
                psOrder.setDate(2, Date.valueOf(LocalDate.now()));
                psOrder.executeUpdate();

                ResultSet generatedKeys = psOrder.getGeneratedKeys();
                int orderId;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }

                // Insert order item with product and quantity
                String insertItemSql = "INSERT INTO order_items (order_id, product_id, order_quantity) VALUES (?, ?, ?)";
                try (PreparedStatement psItem = conn.prepareStatement(insertItemSql)) {
                    psItem.setInt(1, orderId);
                    psItem.setInt(2, productId);
                    psItem.setInt(3, quantity);
                    psItem.executeUpdate();
                }
            }

            conn.commit();

            // Redirect to orders page
            response.sendRedirect(request.getContextPath() + "/OrderController");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error processing Buy Now", e);
        }
    }
}

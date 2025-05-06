package com.littleangels.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.littleangels.config.DbConfig;
import com.littleangels.model.ProductModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/admin-product")
public class AdminProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                listProducts(request, response);
            } else if (action.equals("delete")) {
                deleteProduct(request, response);
            } else if (action.equals("edit")) {
                showEditForm(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                insertProduct(request, response);
            } else if ("update".equals(action)) {
                updateProduct(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<ProductModel> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = DbConfig.getDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProductModel p = new ProductModel(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getString("product_image")
                );
                list.add(p);
            }
        }

        request.setAttribute("productList", list);
        request.getRequestDispatcher("/WEB-INF/pages/admin-product.jsp").forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String name = request.getParameter("product_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        String image = request.getParameter("product_image");

        String sql = "INSERT INTO product (product_name, price, category_id, product_image) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, categoryId);
            ps.setString(4, image);
            ps.executeUpdate();
        }

        response.sendRedirect("products?action=list");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("product_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        String image = request.getParameter("product_image");

        String sql = "UPDATE product SET product_name=?, price=?, category_id=?, product_image=? WHERE product_id=?";

        try (Connection conn = DbConfig.getDbConnection()) {
            // Step 1: Disable foreign key checks
            disableForeignKeyChecks(conn);

            // Step 2: Perform the update
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setInt(3, categoryId);
                ps.setString(4, image);
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            // Step 3: Re-enable foreign key checks
            enableForeignKeyChecks(conn);
        }

        // Redirect back to the admin product page
        response.sendRedirect("admin-product?action=list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("product_id"));
        String sql = "DELETE FROM product WHERE product_id=?";

        try (Connection conn = DbConfig.getDbConnection()) {
            // Step 1: Disable foreign key checks
            disableForeignKeyChecks(conn);

            // Step 2: Perform the deletion
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            // Step 3: Re-enable foreign key checks
            enableForeignKeyChecks(conn);
        }

        // Redirect back to the admin product page
        response.sendRedirect("admin-product?action=list");
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        int id = Integer.parseInt(request.getParameter("product_id"));
        ProductModel existingProduct = null;

        // Get the product by ID to display in the form
        String sql = "SELECT * FROM product WHERE product_id=?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existingProduct = new ProductModel(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getDouble("price"),
                            rs.getInt("category_id"),
                            rs.getString("product_image")
                    );
                }
            }
        }

        if (existingProduct != null) {
            request.setAttribute("product", existingProduct);
            request.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(request, response);
        } else {
            response.sendRedirect("products?action=list");
        }
    }
    private void disableForeignKeyChecks(Connection conn) throws SQLException {
        String query = "SET FOREIGN_KEY_CHECKS = 0";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error disabling foreign key checks: " + ex.getMessage());
        }
    }

    private void enableForeignKeyChecks(Connection conn) throws SQLException {
        String query = "SET FOREIGN_KEY_CHECKS = 1";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error enabling foreign key checks: " + ex.getMessage());
        }
    }

}

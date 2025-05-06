package com.littleangels.controller;

import com.littleangels.model.ProductModel;
import com.littleangels.util.ImageUtil;
import com.littleangels.util.ValidationUtil;
import com.littleangels.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/add-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ImageUtil imageUtil = new ImageUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward to the Add Product form page
        req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Step 1: Validate the product form
            String validationMessage = validateProductForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }

            // Step 2: Extract the product details
            ProductModel productModel = extractProductModel(req);
            Part imagePart = req.getPart("product_image");

            // Validate image format
            if (!ValidationUtil.isValidImageExtension(imagePart)) {
                handleError(req, resp, "Invalid image format. Please upload a jpg, jpeg, png, or gif image.");
                return;
            }

            // Step 3: Add the product to the database
            String errorMessage = addProductToDatabase(productModel);

            if (errorMessage == null) {
                handleSuccess(req, resp, "Product added successfully!", "/admin-product");
            } else {
                handleError(req, resp, errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace(); // still prints to console (recommended)

            // Send exact error message to the browser:
            resp.setContentType("text/plain");
            resp.getWriter().println("An error occurred: " + e.getClass().getName() + " - " + e.getMessage());
        }

    }

    // Step 1: Validate the product form
    private String validateProductForm(HttpServletRequest req) {
        String productName = req.getParameter("product_name");
        String categoryId = req.getParameter("category_id");
        String productPrice = req.getParameter("price");

        if (ValidationUtil.isNullOrEmpty(productName)) return "Product name is required.";
        if (ValidationUtil.isNullOrEmpty(categoryId)) return "Category ID is required.";
        if (ValidationUtil.isNullOrEmpty(productPrice)) return "Product price is required.";

        return null;
    }

    // Step 2: Extract product data and handle image upload
    private ProductModel extractProductModel(HttpServletRequest req) throws IOException, ServletException {
        String productName = req.getParameter("product_name");
        String categoryId = req.getParameter("category_id");
        String productPrice = req.getParameter("price");
        // Handle image upload
        Part imagePart = req.getPart("product_image");
        String imageFileName = imageUtil.getImageNameFromPart(imagePart);
        String rootPath = req.getServletContext().getRealPath("/");
        String saveFolder = "uploads";

        boolean isUploaded = imageUtil.uploadImage(imagePart, rootPath, saveFolder);
        if (!isUploaded) {
            System.out.println("Image upload failed.");
        }

        String imageDbPath = "resources/images/" + saveFolder + "/" + imageFileName;

        // Return a ProductModel object
        return new ProductModel(0, productName, Double.parseDouble(productPrice),
                Integer.parseInt(categoryId), imageDbPath);
    }

    // Step 3: Add product to the database
    private String addProductToDatabase(ProductModel productModel) throws ClassNotFoundException {
        String query = "INSERT INTO product (product_name, price, category_id, product_image) VALUES (?, ?, ?, ?)";

        try (Connection dbConn = DbConfig.getDbConnection();
             PreparedStatement preparedStatement = dbConn.prepareStatement(query)) {

            preparedStatement.setString(1, productModel.getProductName());
            preparedStatement.setDouble(2, productModel.getPrice());
            preparedStatement.setInt(3, productModel.getCategoryId());
            preparedStatement.setString(4, productModel.getProductImage());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0 ? null : "No rows were inserted.";
            
        } catch (SQLException ex) {
            System.err.println("Error inserting product: " + ex.getMessage());
            
            return "Database error occurred during product addition.";
        }
    }
    
    // Step 4: Handle success response
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage) throws IOException {
        resp.sendRedirect(req.getContextPath() + redirectPage);
    }


    // Step 5: Handle error response
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("error", message);
        System.err.println(message);
        req.setAttribute("product_name", req.getParameter("product_name"));
        req.setAttribute("category_id", req.getParameter("category_id"));
        req.setAttribute("price", req.getParameter("price"));
        req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
    }
}

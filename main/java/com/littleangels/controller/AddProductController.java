package com.littleangels.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

import com.littleangels.config.DbConfig;
import com.littleangels.model.ProductModel;

/**
 * Adds product into the database and validates form fields
 * 
 * @author Aarya Gautam
 */

@WebServlet("/add-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB threshold after which files are written to disk
		maxFileSize = 5 * 1024 * 1024, // Maximum file size allowed for upload: 5MB
		maxRequestSize = 20 * 1024 * 1024 // Max size of entire multipart/form-data request: 20MB
)
public class AddProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests to display the add or edit product form. If a
	 * "product_id" parameter is present, loads the existing product data to
	 * pre-fill the form.
	 *
	 * @param req  HttpServletRequest object containing client request data,
	 *             including parameters like "product_id"
	 * @param resp HttpServletResponse object used to forward the request to the JSP
	 *             page for rendering
	 * @throws ServletException if a servlet error occurs during request forwarding
	 * @throws IOException      if an input/output error occurs during request
	 *                          forwarding
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get product_id parameter from request
		String idStr = req.getParameter("product_id");
		if (idStr != null && !idStr.isEmpty()) {
			int productId = Integer.parseInt(idStr);
			try (Connection conn = DbConfig.getDbConnection()) {
				String sql = "SELECT * FROM product WHERE product_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, productId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					// Create ProductModel object from DB values
					ProductModel product = new ProductModel(rs.getInt("product_id"), rs.getString("product_name"),
							rs.getDouble("price"), rs.getString("product_image"));
					// Set product as request attribute for JSP to access
					req.setAttribute("product", product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Forward request to JSP page for add/update form
		req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
	}

	/**
	 * Handles POST requests for adding a new product or updating an existing
	 * product. Processes form inputs including product name, price, optional image
	 * file, and action type ("add" or "update").
	 *
	 * @param req  HttpServletRequest object containing form data parameters
	 * @param resp HttpServletResponse object used for redirection or forwarding
	 *             after processing
	 * @throws ServletException if a servlet error occurs during request processing
	 *                          or forwarding
	 * @throws IOException      if an input/output error occurs during file upload
	 *                          or response handling
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action"); // Expected values: "add" or "update"
		String name = req.getParameter("product_name");
		String priceStr = req.getParameter("price");
		String productIdStr = req.getParameter("product_id");
		Part imagePart = req.getPart("product_image");

		String imageDbPath = null;

		// If a new image was uploaded, validate and save it
		if (imagePart != null && imagePart.getSize() > 0) {
			String filename = getFileName(imagePart);
			if (!isValidImageExtension(filename)) {
				// Invalid image extension, show error on form
				req.setAttribute("error", "Invalid image format (only JPG, PNG, GIF allowed).");
				req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
				return;
			}

			// Save the uploaded file to the server file system under "uploads" folder
			String uploadDir = req.getServletContext().getRealPath("") + "uploads";
			java.io.File uploadDirFile = new java.io.File(uploadDir);
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}

			String filePath = uploadDir + java.io.File.separator + filename;
			imagePart.write(filePath);

			// Store relative path to image for database
			imageDbPath = "uploads/" + filename;
		}

		// Parse price input and handle invalid input format
		double price;
		try {
			price = Double.parseDouble(priceStr);
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Invalid price format.");
			req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
			return;
		}

		try (Connection conn = DbConfig.getDbConnection()) {
			if ("update".equalsIgnoreCase(action)) {
				// Update existing product
				if (productIdStr == null || productIdStr.isEmpty()) {
					req.setAttribute("error", "Product ID is required for update.");
					req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
					return;
				}
				int productId = Integer.parseInt(productIdStr);

				String sql;
				PreparedStatement stmt;

				if (imageDbPath != null) {
					// Update including new image path
					sql = "UPDATE product SET product_name=?, price=?, product_image=? WHERE product_id=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setDouble(2, price);
					stmt.setString(3, imageDbPath);
					stmt.setInt(4, productId);
				} else {
					// Update without changing the image
					sql = "UPDATE product SET product_name=?, price=? WHERE product_id=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setDouble(2, price);
					stmt.setInt(3, productId);
				}
				int rows = stmt.executeUpdate();
				if (rows > 0) {
					// Redirect to product list page after successful update
					resp.sendRedirect(req.getContextPath() + "/admin-product?action=list");
				} else {
					req.setAttribute("error", "Failed to update product.");
					req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
				}
			} else {
				// Add new product, image must be provided
				if (imageDbPath == null) {
					req.setAttribute("error", "Image is required for new product.");
					req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
					return;
				}
				String sql = "INSERT INTO product (product_name, price, product_image) VALUES (?, ?, ?)";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, name);
					stmt.setDouble(2, price);
					stmt.setString(3, imageDbPath);
					int rows = stmt.executeUpdate();
					if (rows > 0) {
						// Redirect to product list page after successful add
						resp.sendRedirect(req.getContextPath() + "/admin-product?action=list");
					} else {
						req.setAttribute("error", "Failed to add product.");
						req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
					}
				} catch (Exception e) {
					e.printStackTrace();
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting product");
					System.out.println("error");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Database error: " + e.getMessage());
			req.getRequestDispatcher("/WEB-INF/pages/add-product.jsp").forward(req, resp);
		}
	}

	/**
	 * Helper method to extract the filename from the HTTP Part header.
	 * 
	 * @param part the file part from the multipart request
	 * @return the file name string
	 */
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "";
	}

	/**
	 * Validates the file extension of the uploaded image. Only allows .jpg, .jpeg,
	 * .png, and .gif files.
	 * 
	 * @param filename the name of the uploaded file
	 * @return true if the extension is valid, false otherwise
	 */
	private boolean isValidImageExtension(String filename) {
		String lower = filename.toLowerCase();
		return lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png") || lower.endsWith(".gif");
	}
}

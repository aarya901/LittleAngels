package com.littleangels.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;

/**
 * Service class responsible for user registration. It connects to the database
 * and inserts user data into the 'user' table.
 * 
 * @author Aarya Gautam
 */
public class RegisterService {

	private Connection dbConn; // Database connection object

	/**
	 * Constructor: Establishes a database connection using DbConfig. Logs success
	 * or prints the error if connection fails.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection(); // Establish DB connection
			System.out.println("Database connection established successfully.");
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace(); // Print detailed stack trace for debugging
		}
	}

	/**
	 * Adds a new user to the database.
	 *
	 * @param userModel A populated UserModel object containing user input.
	 * @return Null if insertion is successful, or an error message if failed.
	 */
	public String addUser(UserModel userModel) {
		// Check if connection is available
		if (dbConn == null) {
			return "Database connection is not available.";
		}

		// SQL query to insert a new user into the 'user' table
		String insertQuery = "INSERT INTO user (user_name, user_password, user_role, user_phone, user_email, "
				+ "first_name, last_name, address, dob, user_image, gender) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = dbConn.prepareStatement(insertQuery)) {
			// Set values for each placeholder (?) in the SQL query
			preparedStatement.setString(1, userModel.getUserName());
			preparedStatement.setString(2, userModel.getPassword());
			preparedStatement.setString(3, userModel.getRole());
			preparedStatement.setString(4, userModel.getPhone());
			preparedStatement.setString(5, userModel.getEmail());
			preparedStatement.setString(6, userModel.getFirstName());
			preparedStatement.setString(7, userModel.getLastName());
			preparedStatement.setString(8, userModel.getAddress());
			preparedStatement.setDate(9, new Date(userModel.getDob().getTime()));
			preparedStatement.setString(10, userModel.getImagePath());

			// Execute the insert query and check if any rows were inserted
			int rowsInserted = preparedStatement.executeUpdate();
			return rowsInserted > 0 ? null : "No rows were inserted."; // Return null if successful

		} catch (SQLException ex) {
			// Log and handle SQL exceptions
			System.err.println("Error inserting user: " + ex.getMessage());

			// Check for duplicate entry (e.g., duplicate username or email)
			if (ex.getMessage().contains("Duplicate")) {
				return "Username or Email already exists.";
			}

			return "Database error occurred during registration.";
		}
	}
}

package com.littleangels.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;
import com.littleangels.util.PasswordUtil;

/**
 * Service class responsible for handling user login functionality. It connects
 * to the database, verifies user credentials, and returns a populated UserModel
 * object if authentication is successful.
 * 
 * @author Rachina Gosai
 */
public class LoginService {

	private Connection dbConn; // Connection object to interact with the database
	private boolean isConnectionError = false; // Flag to track database connection issues

	/**
	 * Constructor initializes the database connection. If connection fails, sets
	 * the connection error flag.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection(); // Establish connection using DbConfig
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception
			isConnectionError = true; // Set error flag
		}
	}

	/**
	 * Attempts to log in a user by verifying the provided credentials.
	 *
	 * @param userModel A UserModel object containing input username and password.
	 * @return A UserModel object with user data if login is successful, null
	 *         otherwise.
	 */
	public UserModel loginUser(UserModel userModel) {
		// Return null if connection failed
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		// SQL query to fetch user info based on username
		String sql = "SELECT user_id, user_name, user_password, user_role FROM user WHERE user_name = ?";

		try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
			// Set the username parameter in the query
			ps.setString(1, userModel.getUserName().trim());

			// Execute the query and get result set
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve and trim values from the result set
				String dbUsername = rs.getString("user_name");
				if (dbUsername != null)
					dbUsername = dbUsername.trim();

				String dbPassword = rs.getString("user_password");
				if (dbPassword != null)
					dbPassword = dbPassword.trim();

				String dbRoleRaw = rs.getString("user_role");
				String dbRole = (dbRoleRaw != null) ? dbRoleRaw.trim() : "";

				int dbUserId = rs.getInt("user_id");

				// Decrypt password from database using username as key
				String decrypted = PasswordUtil.decrypt(dbPassword, dbUsername);
				if (decrypted == null) {
					System.out.println("Password decryption failed.");
					return null;
				}

				// Compare input credentials with database values
				if (dbUsername.equalsIgnoreCase(userModel.getUserName().trim())
						&& decrypted.equals(userModel.getPassword().trim())) {

					// If match found, populate and return a new UserModel
					UserModel loggedIn = new UserModel();
					loggedIn.setUserId(dbUserId);
					loggedIn.setUserName(dbUsername);
					loggedIn.setRole(dbRole); // Set role from DB, not from form

					return loggedIn;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log SQL exceptions
		}

		// Return null if login fails
		return null;
	}
}

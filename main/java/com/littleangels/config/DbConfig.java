package com.littleangels.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configuration class responsible for establishing a database connection to the
 * Little Angels MySQL database.
 * 
 * @author Aarya Gautam
 */
public class DbConfig {

	// Database configuration information
	private static final String DB_NAME = "little_angels";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	private static final String USERNAME = "root";
	private static final String PASSWORD = ""; // Set your MySQL password here

	/**
	 * Establishes and returns a connection to the MySQL database using JDBC.
	 *
	 * @throws SQLException           if a database access error occurs
	 * @throws ClassNotFoundException if the MySQL JDBC driver class is not found
	 */
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
		// Load MySQL JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Create and return a database connection
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}

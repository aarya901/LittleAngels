package com.littleangels.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;

public class RegisterService {

    private Connection dbConn;

    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
            System.out.println("Database connection established successfully.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String addUser(UserModel userModel) {
        if (dbConn == null) {
            return "Database connection is not available.";
        }

        String insertQuery = "INSERT INTO user (user_name, user_password, user_role, user_phone, user_email, first_name, last_name, address, dob,"
        		+ " user_image, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = dbConn.prepareStatement(insertQuery)) {
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
            preparedStatement.setString(11, userModel.getGender());  // Set gender value

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0 ? null : "No rows were inserted.";

        } catch (SQLException ex) {
            System.err.println("Error inserting user: " + ex.getMessage());
            if (ex.getMessage().contains("Duplicate")) {
                return "Username or Email already exists.";
            }
            return "Database error occurred during registration.";
        }
    }
}

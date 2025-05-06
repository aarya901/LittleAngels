package com.littleangels.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.littleangels.config.DbConfig;
import com.littleangels.model.UserModel;
import com.littleangels.util.PasswordUtil;

/**
 * Service class for handling login operations.
 * Connects to the database, verifies user credentials, and returns login status.
 */
public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Validates the user credentials against the database records.
     *
     * @param userModel the UserModel object containing login input
     * @return true if login is successful, false otherwise; null if connection error
     */
    public Boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT user_name, user_password, user_role FROM user WHERE user_name = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUserName());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return validatePassword(result, userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return false;
    }

    /**
     * Validates the username, decrypted password, and role.
     *
     * @param result    the ResultSet containing DB user data
     * @param userModel the login data from form
     * @return true if everything matches, false otherwise
     * @throws SQLException
     */
    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("user_name").trim();
        String dbPassword = result.getString("user_password").trim();
        String dbRole = result.getString("user_role").trim();

        String inputUsername = userModel.getUserName().trim();
        String inputPassword = userModel.getPassword().trim();
        String inputRole = userModel.getRole().trim();

        String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

        if (decryptedPassword == null) {
            System.out.println("Decryption failed.");
            return false;
        }

        return dbUsername.equalsIgnoreCase(inputUsername)
                && decryptedPassword.equals(inputPassword)
                && dbRole.equalsIgnoreCase(inputRole);
    }
}

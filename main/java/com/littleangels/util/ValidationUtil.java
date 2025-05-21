package com.littleangels.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

/**
 * Utility class providing common input validation methods.
 * 
 * Author: Aarya Gautam
 */
public class ValidationUtil {

	/**
	 * 1. Checks if a given string is null or empty after trimming.
	 * 
	 * @param value The input string.
	 * @return true if null or empty, false otherwise.
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * 2. Validates if the string contains only alphabetic characters (a–z, A–Z).
	 * 
	 * @param value The input string.
	 * @return true if alphabetic, false otherwise.
	 */
	public static boolean isAlphabetic(String value) {
		return value != null && value.matches("^[a-zA-Z]+$");
	}

	/**
	 * 3. Validates if the string starts with a letter and contains only letters and
	 * numbers.
	 * 
	 * @param value The input string.
	 * @return true if valid, false otherwise.
	 */
	public static boolean isAlphanumericStartingWithLetter(String value) {
		return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * 4. Validates if the string equals "male" or "female", case insensitive.
	 * 
	 * @param value The input gender string.
	 * @return true if valid gender, false otherwise.
	 */
	public static boolean isValidGender(String value) {
		return value != null && (value.equalsIgnoreCase("male") || value.equalsIgnoreCase("female"));
	}

	/**
	 * 5. Validates if the string is in a valid email format.
	 * 
	 * @param email The input email string.
	 * @return true if valid email format, false otherwise.
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	/**
	 * 6. Validates if the string is a valid 10-digit Nepali phone number starting
	 * with 98.
	 * 
	 * @param number The phone number string.
	 * @return true if valid number, false otherwise.
	 */
	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	/**
	 * 7. Validates if the password has at least 1 uppercase letter, 1 digit, and 1
	 * special character. Minimum length is 8 characters.
	 * 
	 * @param password The input password string.
	 * @return true if strong password, false otherwise.
	 */
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);
	}

	/**
	 * 8. Validates if the uploaded file (Part) has an image extension: jpg, jpeg,
	 * png, or gif.
	 * 
	 * @param imagePart The Part object containing file data.
	 * @return true if file is a valid image, false otherwise.
	 */
	public static boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}

	/**
	 * 9. Validates if the password and retype password fields match exactly.
	 * 
	 * @param password       The original password.
	 * @param retypePassword The retyped password.
	 * @return true if both passwords match, false otherwise.
	 */
	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return password != null && password.equals(retypePassword);
	}

	/**
	 * 10. Validates if a given date of birth indicates an age of at least 16 years.
	 * 
	 * @param dob The LocalDate representing the date of birth.
	 * @return true if age is 16 or more, false otherwise.
	 */
	public static boolean isAgeAtLeast16(LocalDate dob) {
		if (dob == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 16;
	}
}

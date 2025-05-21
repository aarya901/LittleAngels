package com.littleangels.model;

import java.util.Date;

/**
 * Represents a user with personal, contact, and authentication details.
 * 
 * @author Aarya Gautam
 */
public class UserModel {
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String role;
	private Date dob;
	private String imagePath;
	private String gender;

	/**
	 * Default constructor.
	 */
	public UserModel() {
	}

	/**
	 * Parameterized constructor to initialize all fields.
	 * 
	 * @param userId    the user's ID
	 * @param firstName the user's first name
	 * @param lastName  the user's last name
	 * @param userName  the user's username
	 * @param password  the user's password
	 * @param phone     the user's phone number
	 * @param email     the user's email
	 * @param address   the user's address
	 * @param role      the user's role
	 * @param dob       the user's date of birth
	 * @param imagePath the user's profile image path
	 * @param gender    the user's gender
	 */
	public UserModel(int userId, String firstName, String lastName, String userName, String password, String phone,
			String email, String address, String role, Date dob, String imagePath, String gender) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.role = role;
		this.dob = dob;
		this.imagePath = imagePath;
		this.gender = gender;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the username.
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username.
	 * 
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the phone number.
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number.
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the role.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 * 
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the date of birth.
	 * 
	 * @return dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth.
	 * 
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the image path.
	 * 
	 * @return imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path.
	 * 
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the gender.
	 * 
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 * 
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
}

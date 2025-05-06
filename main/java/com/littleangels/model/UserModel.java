package com.littleangels.model;

import java.util.Date;

public class UserModel {

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
    private String gender;  // New field for gender

    // Constructor
    public UserModel(String firstName, String lastName, String userName, String password, String phone, 
                     String email, String address, String role, Date dob, String imagePath, String gender) {
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
        this.gender = gender;  // Set gender
    }

    public UserModel() {
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGender() {
        return gender;  // Getter for gender
    }

    public void setGender(String gender) {
        this.gender = gender;  // Setter for gender
    }
}

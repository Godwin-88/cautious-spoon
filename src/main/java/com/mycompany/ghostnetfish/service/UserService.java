/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.service;

import com.mycompany.ghostnetfish.dao.UserDAO;
import com.mycompany.ghostnetfish.model.User;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    // Constructor injection of UserDAO
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Register a new user
    public void registerUser(String name, String phoneNumber, User.Role role, String password) {
        // Validate user details (basic example)
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        // Create a new User object
        User user = new User(name, phoneNumber, role, password);

        // Save the user to the database
        userDAO.save(user);
    }

    // Find a user by ID
    public User getUserById(int id) {
        // Validate ID
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        return userDAO.findById(id);
    }

    // Find a user by name
    public User getUserByName(String name) {
        // Validate name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }

        return userDAO.findByName(name);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    // Update a user's details
    public void updateUser(int id, String name, String phoneNumber, User.Role role, String password) {
        // Retrieve the existing user
        User user = getUserById(id);

        // Update user details
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            user.setPhoneNumber(phoneNumber);
        }

        if (role != null) {
            user.setRole(role);
        }

        if (password != null && password.length() >= 6) {
            user.setPassword(password);
        }

        // Save the updated user back to the database
        userDAO.save(user);
    }

    // Delete a user by ID
    public void deleteUser(int id) {
        // Retrieve the existing user
        User user = getUserById(id);

        if (user != null) {
            // Logic to remove the user (this method would need to be implemented in UserDAO)
            // Example:
            // userDAO.delete(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    // Additional business logic methods can be added here
}


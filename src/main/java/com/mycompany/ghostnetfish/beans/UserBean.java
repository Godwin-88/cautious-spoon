/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.UserService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserBean {

    private String name;
    private String phoneNumber;
    private String password;
    private User.Role role;  // Role can be RECOVERER or REPORTER

    private UserService userService;

    public UserBean() {
        // Constructor - typically UserService would be injected
    }

    // Register a new user
    public String registerUser() {
        try {
            userService.registerUser(name, phoneNumber, role, password);
            return "login?faces-redirect=true";  // Redirect to login page after registration
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(e.getMessage()));
            return null;  // Stay on the same page if registration fails
        }
    }

    // Getters and Setters for the form fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public User.Role getRole() { return role; }
    public void setRole(User.Role role) { this.role = role; }
}

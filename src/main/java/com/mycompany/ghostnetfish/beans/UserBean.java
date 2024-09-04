/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@ManagedBean
@RequestScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;
    private String password;
    private User.Role role;  // Role can be RECOVERER or REPORTER

    private UserService userService;

    // Constructor - typically UserService would be injected via @Inject or CDI
    public UserBean() {
        // Manual instantiation for simplicity, should use @Inject or @EJB in a real application
        userService = new UserService();  // Replace with actual dependency injection
    }

    // Register a new user
    public String registerUser() {
        try {
            // Call UserService to register the user
            userService.registerUser(name, phoneNumber, role, password);
            // Redirect to login page upon successful registration
            return "login?faces-redirect=true";  
        } catch (IllegalArgumentException e) {
            // Add an error message to the JSF context if registration fails
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return null;  // Stay on the same page if registration fails
        }
    }

    // Getters and Setters for the form fields
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    
    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public User.Role getRole() { 
        return role; 
    }
    
    public void setRole(User.Role role) { 
        this.role = role; 
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

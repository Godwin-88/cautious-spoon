/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.UserService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private User loggedInUser;
    private UserService userService;

    // Constructor for dependency injection
    public LoginBean() {
        // Typically, userService would be injected with CDI or some DI framework.
        // For example: @Inject private UserService userService;
    }

    // Authenticate the user
    public String login() {
        try {
            // Fetch the user by username
            loggedInUser = userService.getUserByName(username);

            if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                // Check the user's role and redirect accordingly
                if (loggedInUser.getRole() == User.Role.REPORTER) {
                    return "reportGhostNet?faces-redirect=true";  // Redirect for reporters
                } else if (loggedInUser.getRole() == User.Role.RECOVERER) {
                    return "recoverGhostNet?faces-redirect=true";  // Redirect for recoverers
                }
            } else {
                // Add an error message for invalid credentials
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credentials", null));
            }
        } catch (Exception e) {
            // Add an error message if an exception occurs
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error during login: " + e.getMessage(), null));
        }
        return null;  // Stay on the same login page if authentication fails
    }

    // Log out the user
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";  // Redirect to login page after logout
    }

    // Check if the user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Check if the logged-in user is a reporter
    public boolean isReporter() {
        return loggedInUser != null && loggedInUser.getRole() == User.Role.REPORTER;
    }

    // Check if the logged-in user is a recoverer
    public boolean isRecoverer() {
        return loggedInUser != null && loggedInUser.getRole() == User.Role.RECOVERER;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

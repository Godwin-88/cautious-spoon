/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.UserService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private User loggedInUser;
    private UserService userService;

    // Constructor for dependency injection
    public LoginBean() {
        // userService would typically be injected via @Inject or @EJB in a real setup
    }

    // Authenticate the user
    public String login() {
        try {
            loggedInUser = userService.getUserByName(username);

            if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                // Check user role and redirect accordingly
                if (loggedInUser.getRole() == User.Role.REPORTER) {
                    return "reportGhostNet?faces-redirect=true";  // Reporter-specific page
                } else if (loggedInUser.getRole() == User.Role.RECOVERER) {
                    return "recoverGhostNet?faces-redirect=true";  // Recoverer-specific page
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Invalid login credentials"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Error during login: " + e.getMessage()));
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
}

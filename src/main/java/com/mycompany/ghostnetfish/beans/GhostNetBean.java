/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.GhostNet;
import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.GhostNetService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

import java.util.List;

@ManagedBean
@RequestScoped
public class GhostNetBean {

    private String location;
    private float estimatedSize;
    private GhostNet.Status status;
    private User reporter;
    private double latitude;  // Include latitude
    private double longitude; // Include longitude

    private GhostNetService ghostNetService;

    // Constructor for injecting GhostNetService
    public GhostNetBean() {
        // Dependency injection would be used in a real setup
        // ghostNetService = InjectedGhostNetService;
    }

    // Report a new ghost net, including latitude and longitude
    public String reportGhostNet() {
        try {
            User loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
            if (loggedInUser != null && loggedInUser.getRole() == User.Role.REPORTER) {
                // Corrected constructor call to include latitude and longitude
                ghostNetService.reportGhostNet(location, estimatedSize, status, loggedInUser, latitude, longitude);
                return "ghostNets?faces-redirect=true";  // Redirect to ghost nets page
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Only reporters can report ghost nets"));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    // Get all ghost nets
    public List<GhostNet> getAllGhostNets() {
        return ghostNetService.getAllGhostNets();
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(float estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public GhostNet.Status getStatus() {
        return status;
    }

    public void setStatus(GhostNet.Status status) {
        this.status = status;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

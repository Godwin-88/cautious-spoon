/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.GhostNet;
import com.mycompany.ghostnetfish.service.GhostNetService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class GhostNetAnonymousBean {

    private String location;
    private float estimatedSize;
    private GhostNet.Status status;

    private GhostNetService ghostNetService;

    // Constructor for dependency injection of GhostNetService
    public GhostNetAnonymousBean() {
        // The GhostNetService would be injected here
        // ghostNetService = InjectedGhostNetService;
    }

    // Anonymous reporting of ghost net
    public String reportAnonymousGhostNet() {
        try {
            // Creating an anonymous ghost net with no reporter
            GhostNet ghostNet = new GhostNet(location, estimatedSize, status, null, null);

            // Call the service to save the ghost net
            ghostNetService.reportGhostNet(ghostNet.getLocation(), ghostNet.getEstimatedSize(), ghostNet.getStatus(), null);

            // Redirect to a confirmation page
            return "reportSuccess?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Error while reporting: " + e.getMessage()));
            return null;
        }
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
}

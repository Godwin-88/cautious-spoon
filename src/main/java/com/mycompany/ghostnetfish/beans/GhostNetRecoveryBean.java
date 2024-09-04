/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.GhostNet;
import com.mycompany.ghostnetfish.model.User;
import com.mycompany.ghostnetfish.service.GhostNetService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;

import java.util.List;

@ManagedBean
@RequestScoped
public class GhostNetRecoveryBean {

    private int selectedGhostNetId;
    private GhostNetService ghostNetService;
    private List<GhostNet> availableGhostNets;

    public GhostNetRecoveryBean() {
        // Constructor - GhostNetService would typically be injected
        availableGhostNets = ghostNetService.getAllGhostNets();  // Load all ghost nets
    }

    // Recover a ghost net
    public String recoverGhostNet() {
        try {
            // Get the logged-in user (recoverer)
            User recoverer = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");

            if (recoverer != null && recoverer.getRole() == User.Role.RECOVERER) {
                // Retrieve the selected ghost net
                GhostNet ghostNet = ghostNetService.getGhostNetById(selectedGhostNetId);

                // Mark the ghost net as recovered and assign the recoverer
                ghostNetService.updateGhostNet(ghostNet.getId(), GhostNet.Status.RECOVERED, recoverer);

                return "recoverySuccess?faces-redirect=true";  // Redirect to success page
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("You must be a recoverer to recover ghost nets."));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Error during recovery: " + e.getMessage()));
            return null;
        }
    }

    // Getters and Setters
    public int getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(int selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }

    public List<GhostNet> getAvailableGhostNets() {
        return availableGhostNets;
    }
}


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

    private GhostNetService ghostNetService;
    private List<GhostNet> ghostNetsNeedingRecovery;
    private int selectedGhostNetId;  // ID of the ghost net selected for recovery

    public GhostNetRecoveryBean() {
        // Initialize the ghost nets needing recovery (you would inject ghostNetService here)
        ghostNetsNeedingRecovery = ghostNetService.getGhostNetsNeedingRecovery();
    }

    // Mark the selected ghost net as recovered
    public String markAsRecovered() {
        try {
            // Get the logged-in recoverer
            User recoverer = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");

            // Ensure the user is a recoverer
            if (recoverer != null && recoverer.getRole() == User.Role.RECOVERER) {
                // Mark the selected ghost net as recovered
                ghostNetService.markGhostNetAsRecovered(selectedGhostNetId, recoverer);

                return "recoverySuccess?faces-redirect=true";  // Redirect to success page
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("You must be a recoverer to mark ghost nets as recovered."));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Error while recovering ghost net: " + e.getMessage()));
            return null;
        }
    }

    // Getters and Setters
    public List<GhostNet> getGhostNetsNeedingRecovery() {
        return ghostNetsNeedingRecovery;
    }

    public int getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(int selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }
}

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

import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class GhostNetRecoveryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private GhostNetService ghostNetService;
    private List<GhostNet> ghostNetsNeedingRecovery;
    private int selectedGhostNetId;  // ID of the ghost net selected for recovery

    // Constructor
    public GhostNetRecoveryBean() {
        // Ideally, GhostNetService would be injected here using @Inject or through a dependency injection framework
        ghostNetService = new GhostNetService();  // Assuming manual instantiation for this example

        // Fetch ghost nets needing recovery
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

                // Redirect to success page
                return "recoverySuccess?faces-redirect=true";
            } else {
                // Add error message if the user is not a recoverer
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must be a recoverer to mark ghost nets as recovered.", null));
                return null;
            }
        } catch (Exception e) {
            // Add error message if an exception occurs
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while recovering ghost net: " + e.getMessage(), null));
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

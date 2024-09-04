/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.beans;

import com.mycompany.ghostnetfish.model.GhostNet;
import com.mycompany.ghostnetfish.service.GhostNetService;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;

import java.util.List;

@ManagedBean
@RequestScoped
public class UnrecoveredGhostNetsBean {

    private GhostNetService ghostNetService;
    private List<GhostNet> unrecoveredGhostNets;

    public UnrecoveredGhostNetsBean() {
        // Typically, GhostNetService would be injected here
        unrecoveredGhostNets = ghostNetService.getGhostNetsNeedingRecovery();  // Load all unrecovered ghost nets
    }

    // Getter for the unrecovered ghost nets
    public List<GhostNet> getUnrecoveredGhostNets() {
        return unrecoveredGhostNets;
    }
}

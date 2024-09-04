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
public class GhostNetNeedingRecoveryBean {

    private GhostNetService ghostNetService;
    private List<GhostNet> ghostNetsNeedingRecovery;

    public GhostNetNeedingRecoveryBean() {
        // Typically ghostNetService would be injected here
        ghostNetsNeedingRecovery = ghostNetService.getGhostNetsNeedingRecovery();  // Get ghost nets that need recovery
    }

    public List<GhostNet> getGhostNetsNeedingRecovery() {
        return ghostNetsNeedingRecovery;
    }
}

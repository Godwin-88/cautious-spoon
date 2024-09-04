/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.service;

import com.mycompany.ghostnetfish.dao.GhostNetDAO;
import com.mycompany.ghostnetfish.model.GhostNet;
import com.mycompany.ghostnetfish.model.User;
import java.util.List;

public class GhostNetService {

    private GhostNetDAO ghostNetDAO;

    // Constructor injection of GhostNetDAO
    public GhostNetService(GhostNetDAO ghostNetDAO) {
        this.ghostNetDAO = ghostNetDAO;
    }

    // Register a new ghost net
    public void reportGhostNet(String location, float estimatedSize, GhostNet.Status status, User reporter) {
    // Validate ghost net details (basic example)
    if (location == null || location.isEmpty()) {
        throw new IllegalArgumentException("Location cannot be null or empty");
    }

    if (estimatedSize <= 0) {
        throw new IllegalArgumentException("Estimated size must be greater than 0");
    }

    if (status == null) {
        throw new IllegalArgumentException("Status cannot be null");
    }

    // Create a new GhostNet object
    GhostNet ghostNet = new GhostNet(location, estimatedSize, status, reporter, null);

    // Save the ghost net to the database
    ghostNetDAO.save(ghostNet);
}


    // Find a ghost net by ID
    public GhostNet getGhostNetById(int id) {
        // Validate ID
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        return ghostNetDAO.findById(id);
    }

    // Get all ghost nets
    public List<GhostNet> getAllGhostNets() {
        return ghostNetDAO.findAll();
    }

    // Find ghost nets by status
    public List<GhostNet> getGhostNetsByStatus(GhostNet.Status status) {
        // Validate status
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        return ghostNetDAO.findByStatus(status);
    }

    // Update a ghost net's status and recoverer
    public void updateGhostNet(int id, GhostNet.Status status, User recoverer) {
        // Retrieve the existing ghost net
        GhostNet ghostNet = getGhostNetById(id);

        // Update ghost net status
        if (status != null) {
            ghostNet.setStatus(status);
        }

        // Update recoverer if provided
        if (recoverer != null) {
            ghostNet.setRecoverer(recoverer);
        }

        // Save the updated ghost net back to the database
        ghostNetDAO.save(ghostNet);
    }

    // Additional business logic methods can be added here
}

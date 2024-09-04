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
    public void reportGhostNet(String location, float estimatedSize, GhostNet.Status status, User reporter, double latitude, double longitude) {
        // Validate ghost net details (basic validation)
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }

        if (estimatedSize <= 0) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }

        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        // Create a new GhostNet object with latitude and longitude
        GhostNet ghostNet = new GhostNet(location, estimatedSize, status, reporter, latitude, longitude);

        // Save the ghost net to the database
        ghostNetDAO.save(ghostNet);
    }

    // Update ghost net status to recovered and assign the recoverer
    public void markGhostNetAsRecovered(int ghostNetId, User recoverer) {
        GhostNet ghostNet = ghostNetDAO.findById(ghostNetId);

        // Validate ghost net
        if (ghostNet == null) {
            throw new IllegalArgumentException("Ghost net not found with ID: " + ghostNetId);
        }

        // Update status to RECOVERED and assign the recoverer
        ghostNet.setStatus(GhostNet.Status.RECOVERED);
        ghostNet.setRecoverer(recoverer);

        // Save the changes
        ghostNetDAO.save(ghostNet);
    }

    // Find a ghost net by ID
    public GhostNet getGhostNetById(int id) {
        // Validate ID
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        GhostNet ghostNet = ghostNetDAO.findById(id);

        // Handle the case where the ghost net is not found
        if (ghostNet == null) {
            throw new IllegalArgumentException("Ghost net not found with ID: " + id);
        }

        return ghostNet;
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
    public void updateGhostNet(int id, GhostNet.Status status, User recoverer, double latitude, double longitude) {
        GhostNet ghostNet = ghostNetDAO.findById(id);

        if (ghostNet == null) {
            throw new IllegalArgumentException("Ghost net not found with ID: " + id);
        }

        if (status != null) {
            ghostNet.setStatus(status);
        }

        if (recoverer != null) {
            ghostNet.setRecoverer(recoverer);
        }

        if (latitude != 0 && longitude != 0) {
            ghostNet.setLatitude(latitude);
            ghostNet.setLongitude(longitude);
        }

        ghostNetDAO.save(ghostNet);
    }

    // Method to get ghost nets that need recovery (i.e., not in RECOVERED status)
    public List<GhostNet> getGhostNetsNeedingRecovery() {
        return ghostNetDAO.findByStatusNot(GhostNet.Status.RECOVERED);
    }

    // Additional business logic methods can be added here as needed
}

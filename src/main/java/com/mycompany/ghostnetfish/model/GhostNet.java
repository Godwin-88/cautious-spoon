/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GhostNet {

    @Id
    private int id;
    private String location;
    private float estimatedSize;
    private Status status;
    
    @ManyToOne
    private User recoverer;

    private double latitude;  // Latitude for map
    private double longitude; // Longitude for map

    // Enum to define the status of the ghost net
    public enum Status {
        REPORTED,
        RESCUE_IMMINENT,
        MISSING,
        RECOVERED
    }

    // Default constructor
    public GhostNet() {
    }

    // Constructor with all fields
    public GhostNet(String location, float estimatedSize, Status status, User recoverer, double latitude, double longitude) {
        this.location = location;
        this.estimatedSize = estimatedSize;
        this.status = status;
        this.recoverer = recoverer;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getRecoverer() {
        return recoverer;
    }

    public void setRecoverer(User recoverer) {
        this.recoverer = recoverer;
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

    // ToString method for debugging purposes
    @Override
    public String toString() {
        return "GhostNet{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", estimatedSize=" + estimatedSize +
                ", status=" + status +
                ", recoverer=" + (recoverer != null ? recoverer.getName() : "None") +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

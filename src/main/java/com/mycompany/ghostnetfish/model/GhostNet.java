/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GhostNets")
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;

    private float estimatedSize;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "recoverer_id")
    private User recoverer;

    public enum Status {
        REPORTED,
        RESCUE_IMMINENT,
        MISSING,
        RECOVERED
    }

    // Constructors
    public GhostNet() {}

    public GhostNet(String location, float estimatedSize, Status status, User reporter, User recoverer) {
        this.location = location;
        this.estimatedSize = estimatedSize;
        this.status = status;
        this.reporter = reporter;
        this.recoverer = recoverer;
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

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getRecoverer() {
        return recoverer;
    }

    public void setRecoverer(User recoverer) {
        this.recoverer = recoverer;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.dao;

import com.mycompany.ghostnetfish.model.GhostNet;
import java.util.List;

public interface GhostNetDAO {
    void save(GhostNet ghostNet);
    GhostNet findById(int id);
    List<GhostNet> findAll();
    List<GhostNet> findByStatus(GhostNet.Status status);
    
    // Find ghost nets where the status is NOT a specific value (for this case, not RECOVERED)
    List<GhostNet> findByStatusNot(GhostNet.Status status);
}


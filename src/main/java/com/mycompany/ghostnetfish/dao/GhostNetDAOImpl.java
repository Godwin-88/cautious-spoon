/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.dao;

import com.mycompany.ghostnetfish.model.GhostNet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class GhostNetDAOImpl implements GhostNetDAO {

    @PersistenceContext(unitName = "GhostNetPU")
    private EntityManager em;

    @Transactional
    @Override
    public void save(GhostNet ghostNet) {
        em.persist(ghostNet);
    }

    @Override
    public GhostNet findById(int id) {
        return em.find(GhostNet.class, id);
    }

    @Override
    public List<GhostNet> findAll() {
        return em.createQuery("SELECT g FROM GhostNet g", GhostNet.class).getResultList();
    }

    @Override
    public List<GhostNet> findByStatus(GhostNet.Status status) {
        return em.createQuery("SELECT g FROM GhostNet g WHERE g.status = :status", GhostNet.class)
                 .setParameter("status", status)
                 .getResultList();
    }

    // Implementation of the missing findByStatusNot method
    @Override
    public List<GhostNet> findByStatusNot(GhostNet.Status status) {
        return em.createQuery("SELECT g FROM GhostNet g WHERE g.status != :status", GhostNet.class)
                 .setParameter("status", status)
                 .getResultList();
    }
}



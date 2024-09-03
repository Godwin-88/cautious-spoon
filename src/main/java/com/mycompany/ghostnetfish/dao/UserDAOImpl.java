/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.dao;


import com.mycompany.ghostnetfish.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "GhostNetPU")
    private EntityManager em;

    @Transactional
    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                 .setParameter("name", name)
                 .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}


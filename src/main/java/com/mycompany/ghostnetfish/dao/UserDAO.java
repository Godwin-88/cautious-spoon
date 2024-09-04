/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ghostnetfish.dao;


import com.mycompany.ghostnetfish.model.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User findById(int id);
    User findByName(String name);
    List<User> findAll();

    public void delete(User user);
}

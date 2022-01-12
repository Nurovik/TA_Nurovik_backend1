package com.blfc.backend.Dao;

import com.blfc.backend.Models.User;

import java.util.List;


public interface UserDao {

    public List<User> getAll();

    public User getusername(String username);

    public int insert(User object);

    public int update(User object);


    public String buatIdKode();

    public User getById(String id);
}

package com.blfc.backend.Dao;

import com.blfc.backend.Models.User;

import java.util.List;


public interface UserDao {

    public List<User> getAll();
    public User getusername(String username);
}

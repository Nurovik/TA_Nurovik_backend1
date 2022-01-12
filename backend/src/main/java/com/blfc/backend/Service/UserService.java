package com.blfc.backend.Service;

import com.blfc.backend.Models.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();

    public User getusername(String username);

    public int insert(User object);

    public int update(User object);


    public String buatIdKode();

    public User getById(String id);
}

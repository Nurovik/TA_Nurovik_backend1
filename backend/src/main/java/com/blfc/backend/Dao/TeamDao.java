package com.blfc.backend.Dao;

import com.blfc.backend.Models.Team;

import java.util.List;

public interface TeamDao {

    public List<Team> getall();
    public Team Getbyid(String namateam);
}

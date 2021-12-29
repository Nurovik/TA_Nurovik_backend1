package com.blfc.backend.Service;

import com.blfc.backend.Models.Team;

import java.util.List;

public interface TeamService {

    public List<Team> getall();
    public Team Getbyid(String namateam);
}

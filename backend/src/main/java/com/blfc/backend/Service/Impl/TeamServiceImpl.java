package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.TeamDao;
import com.blfc.backend.Models.Team;
import com.blfc.backend.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("TeamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;
    @Override
    public List<Team> getall() {

        List<Team> mteam = new ArrayList();
        mteam = teamDao.getall();
        return mteam;
    }

    @Override
    public Team Getbyid(String namateam) {
        Team mteam = new Team();
        try{
            mteam = teamDao.Getbyid(namateam);

            return mteam;

        }catch(Exception e){
            e.printStackTrace();
            return null;

        }
    }
}

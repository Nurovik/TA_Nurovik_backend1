package com.blfc.backend.Dao.Impl;


import com.blfc.backend.Dao.TeamDao;
import com.blfc.backend.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository("teamDao")
public class TeamDaoImpl implements TeamDao {

    private static final String SQL_SELECT_ALL_Team = "select * from team";
    private static final String SQL_SELECT_ID_TEAM = "select * from team where namateam LIKE ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Team> getall() {

        List<Team> listteam = new ArrayList<>();

        listteam = jdbcTemplate.query(SQL_SELECT_ALL_Team , new RowMapper<Team>() {
            @Override
            public Team mapRow(ResultSet rs, int rowNum) throws SQLException {


                Team team = new Team();

                team.setIdteam(rs.getInt("idteam"));
                team.setNamateam(rs.getString("namateam"));
                team.setLogo(rs.getString("logo"));


                return team;
            }
        });



        return listteam;
    }

    @Override
    public Team Getbyid(String namateam) {
        Team mteam = new Team();

        try{
            mteam = jdbcTemplate.queryForObject(SQL_SELECT_ID_TEAM, new Object[]{namateam}, new RowMapper<Team>() {
                @Override
                public Team mapRow(ResultSet rs, int rowNum) throws SQLException {

                    Team team = new Team();

                    team.setIdteam(rs.getInt("idteam"));
                    team.setNamateam(rs.getString("namateam"));
                    team.setLogo(rs.getString("logo"));


                    return team;
                }
            });

            return mteam;
        }catch (Exception e){

            return null;
        }
    }
}

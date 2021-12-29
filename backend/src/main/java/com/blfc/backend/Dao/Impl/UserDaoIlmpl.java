package com.blfc.backend.Dao.Impl;

import com.blfc.backend.Dao.UserDao;
import com.blfc.backend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository("userdao")
public class UserDaoIlmpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_USERNAME = "select * from user where username = ?";
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getusername(String username) {
        User us = new User();
        try {
            us = jdbcTemplate.queryForObject(SQL_SELECT_USERNAME, new Object[]{username}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User use = new User();

                    use.setId_user(rs.getInt("id_user"));
                    use.setUsername(rs.getString("username"));
                    use.setPassword(rs.getString("password"));
                    use.setDatecreated(rs.getTimestamp("datecreated").getTime());
                    use.setDateupdate(rs.getTimestamp("dateupdate").getTime());
                    return use;

                }
            });
            return us;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}

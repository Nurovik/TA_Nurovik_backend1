package com.blfc.backend.Dao.Impl;

import com.blfc.backend.Dao.UserDao;
import com.blfc.backend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository("userdao")
public class UserDaoIlmpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_USERNAME = "select * from user where username = ?";
    private static final String SQL_ALL_USER = "select * from user";
    private static final String SQL_BUAT_ID = "SELECT IFNULL(MAX(CONVERT(id_user, SIGNED INTEGER)), 0) AS kode FROM user";
    private static final String SQL_INSERT = "INSERT INTO user VALUES(?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user = ?";
    private static final String SQL_GET_BY_ID = "select * from user where id_user = ?";
    private static final String SQL_UPDATE = "UPDATE user SET "

            + "username = ?, "
            + "password = ?, "
            + "dateupdate = ? "
            + "WHERE id_user= ?";
    @Override
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();

        listUser= jdbcTemplate.query(SQL_ALL_USER , new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {


                User mUser = new User();

                mUser.setId_user(rs.getInt("id_user"));
                mUser.setUsername(rs.getString("username"));



                return mUser;
            }
        });



        return listUser;

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

            return null;
        }

    }

    @Override
    public int insert(User object) {
        String   username, password;
        int id_user;
        int oke = 0;

        id_user = object.getId_user();
        username = object.getUsername();
        password = object.getPassword();
        try {
            oke = jdbcTemplate.update(SQL_INSERT, new Object[]{
                    id_user,
                    username,
                    password,
                    new Date(),
                    new Date()

            });
        }catch (Exception E){
            E.printStackTrace();
            oke = 0;
        }

        return oke;
    }

    @Override
    public int update(User object) {
        String   username, password;
        int id_user;
        int oke = 0;

        id_user = object.getId_user();
        username = object.getUsername();
        password = object.getPassword();
        try {
            oke = jdbcTemplate.update(SQL_UPDATE, new Object[]{
                    username,
                    password,
                    new Date(),
                    id_user

            });
        }catch (Exception E){
            E.printStackTrace();
            oke = 0;
        }

        return oke;
    }


    @Override
    public String buatIdKode() {
        int banyakData = jdbcTemplate.queryForObject(SQL_BUAT_ID,null, int.class);
        int nomorBerikutnya = banyakData + 1;
        String urutan = "";
        if (banyakData == 0) {
            urutan = "1";
        } else {
            urutan = String.valueOf(nomorBerikutnya);
        }

        return urutan;
    }

    @Override
    public User getById(String id) {
        User us = new User();
        try {
            us = jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[]{id}, new RowMapper<User>() {
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

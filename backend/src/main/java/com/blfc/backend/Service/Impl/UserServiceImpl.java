package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.UserDao;
import com.blfc.backend.Models.User;
import com.blfc.backend.Service.UserService;
import com.blfc.backend.Utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;


    @Override
    public List<User> getAll() {
    List<User> listUser = new ArrayList<>();

        try {
            listUser = userdao.getAll();
            return listUser;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getusername(String username) {
        User us = new User();
        try {
            us = userdao.getusername(username);
            return us;

        }catch (Exception e){

            return null;
        }
    }

    @Override
    public int insert(User object) {
        User mUser = new User();
        mUser.setId_user(Integer.parseInt(userdao.buatIdKode()));
        mUser.setUsername(object.getUsername());
        mUser.setPassword(MD5.encrypt(object.getPassword()));
        int oke = 0;
        oke = userdao.insert(mUser);

        return oke;
    }

    @Override
    public int update(User object) {
        User mUser = new User();
        mUser.setId_user(object.getId_user());
        mUser.setUsername(object.getUsername());
        mUser.setPassword(MD5.encrypt(object.getPassword()));
        int oke = 0;
        oke = userdao.update(mUser);

        return oke;
    }



    @Override
    public String buatIdKode() {
        String urutan = "";
        urutan = userdao.buatIdKode();

        return urutan;
    }

    @Override
    public User getById(String id) {
        User mUser = new User();
        mUser = userdao.getById(id);
        return mUser;
    }
}

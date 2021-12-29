package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.UserDao;
import com.blfc.backend.Models.User;
import com.blfc.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;


    @Override
    public User getusername(String username) {
        User us = new User();
        try {
            us = userdao.getusername(username);
            return us;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

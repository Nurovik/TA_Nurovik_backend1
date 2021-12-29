package com.blfc.backend.Contrlollers;


import com.blfc.backend.Models.User;
import com.blfc.backend.Service.UserService;
import com.blfc.backend.Utils.Constants;
import com.blfc.backend.Utils.GeneratorJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class UserController {


    @Autowired
    UserService userService;



    @PostMapping("/login")
    public Map<String, Object> login (
             @RequestBody final Map<String, Object> request


    ){
        Map<String, Object> response = new HashMap<>();
        String token;
        User us = new User();
        Map<String, Object> loginMap = (Map<String, Object>) request.get(Constants.DATA_KEY);
        us = userService.getusername(loginMap.get("username").toString());

        if(us != null) {

            if (us.getUsername().equals(loginMap.get("username").toString())) {

                if(us.getPassword().equals(loginMap.get("password").toString())){

                    token = GeneratorJWT.createToken(us.getUsername());
                    response.put(Constants.TOKEN, token);
                    response.put(Constants.STATUS, Constants.SUCCESS);
                    response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);



                }else{
                    response.put("status", "Password Salah");
                    response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);


                }


            } else {
                response.put("status", "username Salah");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

            }
        }else{
            response.put("status", "username belum terdaftar");
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

        }
        return response;


    }
}

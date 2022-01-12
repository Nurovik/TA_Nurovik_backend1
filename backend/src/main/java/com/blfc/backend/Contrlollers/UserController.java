package com.blfc.backend.Contrlollers;


import com.blfc.backend.Models.User;
import com.blfc.backend.Service.UserService;
import com.blfc.backend.Utils.CheckUtil;
import com.blfc.backend.Utils.Constants;
import com.blfc.backend.Utils.GeneratorJWT;
import com.blfc.backend.Utils.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class UserController {


    @Autowired
    UserService userService;

Gson gson = new Gson();

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

                if(MD5.decrypt(us.getPassword()).equals(loginMap.get("password").toString())){

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

    @GetMapping("/user")
    public Map<String, Object> GetAll(
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required =  false, value= "token") String token

    ){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            try{
                //ini cek token
                if (CheckUtil.isNullOrEmpty(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "username tidak boleh kosong");
                    return response;

                } else if (CheckUtil.isNullOrEmpty(token)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "token tidak boleh kosong");
                    return response;
                }

                Claims claims = GeneratorJWT.validateToken(token);

                if (!claims.getId().equals(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "Bukan Token dengan username :" + username);
                    return response;
                }

            }catch (ExpiredJwtException expired){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Expired");
                return response;

            }catch(SignatureException signature){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;

            }catch(Exception e){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;
                //cek token sampai sini
            }


            List<User> userList = userService.getAll();

            if(userList.size() >0 ){
                response.put(Constants.STATUS,Constants.STATUS_DATAFOUND);
                response.put(Constants.LIST, userList);

                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


            }else{

                response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }



        }catch (Exception e){
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);

            e.printStackTrace();

        }

        return response;
    }


    @PostMapping("/user/add")
    public Map<String, Object> add (
            @RequestBody final Map<String, Object> request,
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token
    ){
        Map<String, Object> response = new HashMap<String, Object>();


        try{
            try{
                //ini cek token
                if (CheckUtil.isNullOrEmpty(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "username tidak boleh kosong");
                    return response;

                } else if (CheckUtil.isNullOrEmpty(token)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "token tidak boleh kosong");
                    return response;
                }

                Claims claims = GeneratorJWT.validateToken(token);

                if (!claims.getId().equals(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "Bukan Token dengan username :" + username);
                    return response;
                }

            }catch (ExpiredJwtException expired){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Expired");
                return response;

            }catch(SignatureException signature){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;

            }catch(Exception e){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;
                //cek token sampai sini
            }

            Map<String, Object> loginMap = (Map<String, Object>) request.get(Constants.DATA_KEY);



            String jsonInString = gson.toJson(loginMap);

            System.out.println("Ini Json request :" + jsonInString );
            new User();
            User mUser;

            mUser = gson.fromJson(jsonInString, User.class);

            if(mUser != null){
                String in_username = "";
                in_username = mUser.getUsername().toString();
                mUser.setPassword(MD5.encrypt(mUser.getPassword()));
                new User();
                User cek;

                cek = userService.getusername(in_username);

                if( cek != null){

                    response.put(Constants.STATUS, "Username Tersebut sudah terdaftar");
                    response.put(Constants.STATUS_CODE,Constants.FAILED_CODE);

                }else{

                        int oke = 0;

                        oke = userService.insert(mUser);


                        if (oke > 0) {
                            //  message = "Data Berhasil diInput";
                            response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSSAVED);
                            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


                        } else {
                            response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSSAVED);
                            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

                        }




                }

            }else{
                response.put(Constants.STATUS, Constants.STATUS_DATA_FAILED);
                response.put(Constants.STATUS_CODE,Constants.FAILED_CODE);

            }


        }catch(Exception e) {
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            e.printStackTrace();
        }

        return response;
    }


    //Update User
    @PutMapping("/user/{id}")
    public Map<String, Object> updateUser (
            @PathVariable("id") final String id,
            @RequestBody final Map<String, Object> request,
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token
    ){


        Map<String, Object> response = new HashMap<String, Object>();
        try{
            try{
                //ini cek token
                if (CheckUtil.isNullOrEmpty(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "username tidak boleh kosong");
                    return response;

                } else if (CheckUtil.isNullOrEmpty(token)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "token tidak boleh kosong");
                    return response;
                }

                Claims claims = GeneratorJWT.validateToken(token);

                if (!claims.getId().equals(username)) {

                    response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                    response.put(Constants.STATUS, "Bukan Token dengan username :" + username);
                    return response;
                }

            }catch (ExpiredJwtException expired){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Expired");
                return response;

            }catch(SignatureException signature){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;

            }catch(Exception e){

                response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
                response.put(Constants.STATUS, "Token Failed");
                return response;
                //cek token sampai sini
            }
            int oke;
            Map<String,Object> Usernmap = (Map<String, Object>) request.get(Constants.DATA_KEY);

            String jsonString = new ObjectMapper().writeValueAsString(Usernmap);

            User mUser = new User();
            mUser = gson.fromJson(jsonString, User.class);

            new User();
            User cekid;
            cekid = userService.getById(id);

            if(cekid != null){
                mUser.setId_user(Integer.parseInt(id));
                if(mUser != null){
                    User cek = new User();
                    cek = userService.getusername(mUser.getUsername());
                    if( cek != null){

                        response.put(Constants.STATUS, "Username Tersebut sudah terdaftar");
                        response.put(Constants.STATUS_CODE,Constants.FAILED_CODE);

                    }else {

                            oke = userService.update(mUser);
                            if (oke > 0) {
                                response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSEDIT);
                                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

                            } else {
                                response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSEDIT);
                                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

                            }




                    }

                }else{
                    response.put(Constants.STATUS, Constants.STATUS_DATA_FAILED);
                    response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

                }

            }else{
                response.put(Constants.STATUS, Constants.STATUS_IDFAILED);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

            }


        }catch(Exception e){
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            e.printStackTrace();
        }


        return response;
    }


}

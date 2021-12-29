package com.blfc.backend.Contrlollers;


import com.blfc.backend.Models.News;
import com.blfc.backend.Service.NewsService;
import com.blfc.backend.Utils.BaseUtil;
import com.blfc.backend.Utils.CheckUtil;
import com.blfc.backend.Utils.Constants;
import com.blfc.backend.Utils.GeneratorJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public Map<String, Object> getallnews(
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required =  false, value= "token") String token,
            @RequestParam(required = false) Map<String, String> params

    ){
        Map<String, Object> response = new HashMap<String, Object>();
        int activePage = NumberUtils.toInt(params.get(Constants.ACTIVE_PAGE));
        int start = (activePage - 1) * Constants.GRIDBOX_MAX_ROW_PER_PAGE;
        int limit = Constants.GRIDBOX_MAX_ROW_PER_PAGE;
        String orderBy = params.get(Constants.ORDER);
        Map<String, String> assocParams = params;
        List<News> newsList = newsService.getAll(start, limit, orderBy, assocParams);


        int total = newsService.count();
        if(newsList.size()>0){
            response.put(Constants.LIST, newsList);
            response.put(Constants.STATUS, Constants.STATUS_DATAFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            response.put(Constants.TOTAL, total);


        }else{

            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        }

        return response;
    }


    @GetMapping("/news/{id}")
    public Map<String, Object> getbyid(
            @PathVariable("id") final Integer id
    ){
        Map<String, Object> response = new HashMap<String, Object>();

        News Mnews = new News();
        Mnews = newsService.getbyid(id);

        if(Mnews != null){
            response.put(Constants.DATA_KEY, Mnews);
            response.put(Constants.STATUS, Constants.STATUS_DATAFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }else{
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }


        return response;


    }


    @PostMapping("/news")
    public Map<String, Object> savenews(
            @RequestParam("title")  String title,
            @RequestParam("priview")  String priview,
            @RequestParam("content")  String content,
            @RequestParam("status")  int status,
            @RequestHeader (required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token,
            @RequestParam("file") MultipartFile file
    ) {

        Map<String, Object> response = new HashMap<String, Object>();
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

        int oke ;

         oke= newsService.savenews(username,title,priview,content,file,status);

         switch (oke) {
             case 0:
                 response.put(Constants.STATUS,"failed");
                 response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                 break;
             case 1:
                 response.put(Constants.STATUS,Constants.SUCCESS);
                 response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                 break;
             case 2:
                 response.put(Constants.STATUS,"File Not Found");
                 response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                 break;
             case 3:
                 response.put(Constants.STATUS,"username not found");
                 response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                 break;
         }
        return response;
    }


    @PutMapping("/news")
    public Map<String, Object> updatenews(
            @RequestParam("idnews")  int id_news,
            @RequestParam("title")  String title,
            @RequestParam("priview")  String priview,
            @RequestParam("content")  String content,
            @RequestParam("status")  int status,
            @RequestHeader (required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token,
            @RequestParam("file") MultipartFile file
    ) {

        Map<String, Object> response = new HashMap<String, Object>();
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

        int oke ;

        oke= newsService.update(id_news,username,title,priview,content,file,status);

        switch (oke) {
            case 0:
                response.put(Constants.STATUS,"failed");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                break;
            case 1:
                response.put(Constants.STATUS,Constants.SUCCESS);
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                break;
            case 2:
                response.put(Constants.STATUS,"File Not Found");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                break;
            case 3:
                response.put(Constants.STATUS,"username not found");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                break;
        }
        return response;
    }


    @PutMapping("/news/{id}")
    public Map<String, Object> updatenewsnomedia(
            @RequestHeader(required = false, value = "username")String username,
            @RequestHeader(required =  false, value = "token") String token,
            @PathVariable(value = "id") final int id,
            @RequestBody final Map<String,Object>Request
    ) throws JsonProcessingException {

        Map<String, Object> response = new HashMap<String, Object>();
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
        News news = new News();
            Gson gson = new Gson();
        Map<String,Object> Newsmap = (Map<String, Object>) Request.get(Constants.DATA_KEY);

        String jsonString = new ObjectMapper().writeValueAsString(Newsmap);
        System.out.println("ini Response :" + gson.toJson(Newsmap));
        news = gson.fromJson(jsonString, News.class);

        int oke ;

        oke= newsService.updatenewsnomedia(id,username,news.getTitle(),news.getPriview(),news.getContent(),news.getImage(),news.getStatus());

        switch (oke) {
            case 0:
                response.put(Constants.STATUS,"failed");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                break;
            case 1:
                response.put(Constants.STATUS,Constants.SUCCESS);
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                break;
            case 3:
                response.put(Constants.STATUS,"username not found");
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                break;
        }
        return response;
    }


    //Delete News
    @DeleteMapping("/news/{id}")
    public Map<String, Object> DeleteNews (
            @PathVariable("id") final Integer id,
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token
    ) {

        Map<String, Object> response = new HashMap<String, Object>();
        try {
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

        } catch (ExpiredJwtException expired) {

            response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
            response.put(Constants.STATUS, "Token Expired");
            return response;

        } catch (SignatureException signature) {

            response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
            response.put(Constants.STATUS, "Token Failed");
            return response;

        } catch (Exception e) {

            response.put(Constants.STATUS_CODE, Constants.TOKEN_FAILED);
            response.put(Constants.STATUS, "Token Failed");
            return response;
            //cek token sampai sini
        }

        int oke = 0;
        new News();
        News cekid;
        cekid = newsService.getbyid(id);

        if (cekid != null) {
            oke = newsService.deletenews(id);
            if (oke > 0) {
                response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSDELETE);
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            } else {
                response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSDELETE);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);


            }
        } else {
            response.put(Constants.STATUS, Constants.STATUS_IDFAILED);
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

        }


        return response;
    }



}

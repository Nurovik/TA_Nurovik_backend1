/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import API.ApiConsume;
import ResponseServer.ResponseNews;
import ResponseServer.ResponseNewsById;
import ResponseServer.ResponseStatus;
import Utils.Constants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author teguh
 */
public class NewsDao {
    
     private static String alamat;
     Gson gson = new Gson();
     ApiConsume api = new ApiConsume();
    public ResponseNews GetAll(int activepage, String order, String where){
         ResponseNews resp = new ResponseNews();
         String Response = api.GetAllpage(activepage, order, where, "news");
         
         switch (Response){
        
             case "URLException":
                resp.setStatusCode("02");
                resp.setStatus("URL TIDAK DIKENALI");
             break;
            
             case "IOException":
               resp.setStatusCode("02");
               resp.setStatus("SERVER NOT CONNECT");
             break;
             
             default:
             resp = gson.fromJson(Response.toString(), ResponseNews.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseNews resp1 = gson.fromJson(Response.toString(), ResponseNews.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setListData(resp1.getListData());
                         resp.setTotal(resp1.getTotal());
                     
                     }
             break;
                 
         }
       
        
        return resp;
    }
    public ResponseNewsById getid(int id){
        ResponseNewsById resp = new ResponseNewsById();
         String Response = api.GetByid("news", id);
         
         switch (Response){
        
             case "URLException":
                resp.setStatusCode("02");
                resp.setStatus("URL TIDAK DIKENALI");
             break;
            
             case "IOException":
               resp.setStatusCode("02");
               resp.setStatus("SERVER NOT CONNECT");
             break;
             
             default:
             resp = gson.fromJson(Response.toString(), ResponseNewsById.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseNewsById resp1 = gson.fromJson(Response.toString(), ResponseNewsById.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setData(resp1.getData());
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
    public ResponseStatus postnews(String title, String content, String preview, String path, String fileName, String username, String token, String status){
        
        Map<String, String> req = new HashMap<>();
        req.put("title", title);
        req.put("content", content);
        req.put("priview", preview);
        req.put("status", status);
     
    
    String result = api.POSTMedia(req,path + File.separator
                          + fileName, "news", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus puttnews(String id_news,String title, String content, String preview, String path, String fileName, String username, String token, String status){
    
        
            
       Map<String, String> req = new HashMap<>();
        req.put("title", title);
        req.put("content", content);
        req.put("priview", preview);
        req.put("status", status);
        req.put("idnews", id_news);
        
    String result = api.PUTMedia(req,path + File.separator
                          + fileName, "news", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus putnewsnomedia(String id_news,String title, String content,String preview,String image, String Username, String token,String status){
        
        Map<String, Object> put = new HashMap<>();
        Map<String, String> news = new HashMap<>();
        news.put("title", title);
        news.put("priview", preview);
        news.put("content", content);
        news.put("image", image);
        news.put("status", status);
        put.put("data", news);
        
        String result = api.PUTNomedia(put, "news/"+id_news,Username,token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    
    }
    public ResponseStatus Delete(int id ,String Username, String token){
        ResponseStatus resp = new ResponseStatus();
         String Response = api.Delete("news", id,Username,token);
         
         switch (Response){
        
             case "URLException":
                resp.setStatusCode("02");
                resp.setStatus("URL TIDAK DIKENALI");
             break;
            
             case "IOException":
               resp.setStatusCode("02");
               resp.setStatus("SERVER NOT CONNECT");
             break;
             
             default:
             resp = gson.fromJson(Response.toString(), ResponseStatus.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                       
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
}

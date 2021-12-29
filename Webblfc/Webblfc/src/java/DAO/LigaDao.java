/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import API.ApiConsume;
import ResponseServer.ResponseLiga;
import ResponseServer.ResponseLigaByid;
import ResponseServer.ResponseStatus;
import com.google.gson.Gson;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author teguh
 */
public class LigaDao {
    
    private static String alamat;
     Gson gson = new Gson();
     ApiConsume api = new ApiConsume();
    public ResponseLiga GetLiga(){
         ResponseLiga resp = new ResponseLiga();
         String Response = api.GetAll("liga");
         
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
             resp = gson.fromJson(Response.toString(), ResponseLiga.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseLiga resp1 = gson.fromJson(Response.toString(), ResponseLiga.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setListData(resp1.getListData());
                         resp.setTotal(resp1.getTotal());
                     
                     }
             break;
                 
         }
       
        
        return resp;
    }
    public ResponseStatus postLiga(String namaliga, String jumlahteam, String path, String fileName, String username, String token){
        
        Map<String, String> req = new HashMap<>();
        req.put("namaliga", namaliga);
        req.put("jumlahteam", jumlahteam);
      
     
    
    String result = api.POSTMedia(req,path + File.separator
                          + fileName, "liga", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus puttLiga(String idliga,String namaliga, String jumlahteam, String path, String fileName, String username, String token){
    
        
            
       Map<String, String> req = new HashMap<>();
        req.put("namaliga", namaliga);
        req.put("jumlahteam", jumlahteam);
        req.put("idliga", idliga);

        
    String result = api.PUTMedia(req,path + File.separator
                          + fileName, "liga", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus putLiganomedia(String idliga,String namaliga, String jumlahteam,String logo, String Username, String token,String status){
        
        Map<String, Object> put = new HashMap<>();
        Map<String, String> liga = new HashMap<>();
        liga.put("namaliga", namaliga);
        liga.put("jumlahteam", jumlahteam);
        liga.put("logo", logo);
  
        put.put("data", liga);
        
        String result = api.PUTNomedia(put, "liga/"+idliga,Username,token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    
    }
    public ResponseStatus Delete(int id ,String Username, String token){
        ResponseStatus resp = new ResponseStatus();
         String Response = api.Delete("liga", id,Username,token);
         
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
     public ResponseLigaByid getid(int id){
        ResponseLigaByid resp = new ResponseLigaByid();
         String Response = api.GetByid("liga", id);
         
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
             resp = gson.fromJson(Response.toString(), ResponseLigaByid.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseLigaByid resp1 = gson.fromJson(Response.toString(), ResponseLigaByid.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setData(resp1.getData());
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
    
}

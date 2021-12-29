/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import API.ApiConsume;
import ResponseServer.ResponseJadwal;
import ResponseServer.ResponseKlasmen;
import ResponseServer.ResponseKlasmenByid;
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
public class KlasmenDao {
    
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
    public ResponseKlasmen GetKlasmen(){
    
         ResponseKlasmen resp = new ResponseKlasmen();
         String Response = api.GetAll("klasmen");
         
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
             resp = gson.fromJson(Response.toString(), ResponseKlasmen.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseKlasmen resp1 = gson.fromJson(Response.toString(), ResponseKlasmen.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setListData(resp1.getListData());
                         resp.setTotal(resp1.getTotal());
                     
                     }
             break;
                 
         }
         
         return resp;
    
    }
    public ResponseJadwal GetResponsejadwal(int idliga){
        ResponseJadwal resp = new ResponseJadwal();
         String Response = api.GetLiga("jadwal",idliga);
         
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
             resp = gson.fromJson(Response.toString(), ResponseJadwal.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseJadwal resp1 = gson.fromJson(Response.toString(), ResponseJadwal.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setListData(resp1.getListData());
                         resp.setTotal(resp1.getTotal());
                     
                     }
             break;
                 
         }
         
         return resp;
    }
    public ResponseStatus postliga(String namaliga, String detail,  String path, String fileName, String username, String token){
        
        Map<String, String> req = new HashMap<>();
        req.put("namaliga", namaliga);
        req.put("detail", detail);

     
    
    String result = api.POSTMedia(req,path + File.separator
                          + fileName, "klasmen", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus putklasmen(String id_klasmen,String namaliga, String detail,  String path, String fileName, String username, String token){
    
        
            
       Map<String, String> req = new HashMap<>();
       req.put("namaliga", namaliga);
        req.put("detail", detail);
        req.put("idklasmen", id_klasmen);
        
    String result = api.PUTMedia(req,path + File.separator
                          + fileName, "klasmen", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus putklasmenomedia(String id_klasmen,String namaliga, String detail,String image, String Username, String token){
        
        Map<String, Object> put = new HashMap<>();
        Map<String, String> klasmen = new HashMap<>();
        klasmen.put("namaliga", namaliga);
        klasmen.put("gambar", image);
        klasmen.put("detail", detail);

        put.put("data", klasmen);
        
        String result = api.PUTNomedia(put, "klasmen/"+id_klasmen,Username,token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    
    }
    public ResponseStatus Delete(int id ,String Username, String token){
        ResponseStatus resp = new ResponseStatus();
         String Response = api.Delete("klasmen", id,Username,token);
         
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
    public ResponseKlasmenByid getid(int id){
        ResponseKlasmenByid resp = new ResponseKlasmenByid();
         String Response = api.GetByid("klasmen", id);
         
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
             resp = gson.fromJson(Response.toString(), ResponseKlasmenByid.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponseKlasmenByid resp1 = gson.fromJson(Response.toString(), ResponseKlasmenByid.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setData(resp1.getData());
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
}

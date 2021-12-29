/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import API.ApiConsume;
import ResponseServer.ResponsePemain;
import ResponseServer.ResponseStatus;
import ResponseServer.ResponsepemainById;
import com.google.gson.Gson;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author teguh
 */
public class PemainDao {
    
     private static String alamat;
     Gson gson = new Gson();
       ApiConsume api = new ApiConsume();
     public ResponsePemain GetAll(){
         ResponsePemain resp = new ResponsePemain();
         String Response = api.GetAll("pemain");
         
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
             resp = gson.fromJson(Response.toString(), ResponsePemain.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponsePemain resp1 = gson.fromJson(Response.toString(), ResponsePemain.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setListData(resp1.getListData());
                       
                     
                     }
             break;
                 
         }
       
        
        return resp;
    }
     
    public ResponseStatus postPemain(String nama, String fakultas, String nopunggung, String path, String fileName, String username, String token){
        
        Map<String, String> req = new HashMap<>();
        req.put("nama", nama);
        req.put("fakultas", fakultas);
        req.put("nopunggung", nopunggung);
      
     
    
    String result = api.POSTMedia(req,path + File.separator
                          + fileName, "pemain", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus puttPemain(String id,String nama, String fakultas, String nopunggung, String path, String fileName, String username, String token){
    
        
            
       Map<String, String> req = new HashMap<>();
        req.put("nama", nama);
        req.put("fakultas", fakultas);
        req.put("nopunggung", nopunggung);
        req.put("idpemain", id);

        
    String result = api.PUTMedia(req,path + File.separator
                          + fileName, "pemain", username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponseStatus putpemainnomedia(String id,String nama, String fakultas, String nopunggung,String foto, String Username, String token){
        
        Map<String, Object> put = new HashMap<>();
        Map<String, String> pemain = new HashMap<>();
        pemain.put("nama", nama);
        pemain.put("fakultas", fakultas);
        pemain.put("nopunggung", nopunggung);
        pemain.put("image", foto);
  
        put.put("data", pemain);
        
        String result = api.PUTNomedia(put, "pemain/"+id,Username,token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    
    }
    public ResponseStatus Delete(int id ,String Username, String token){
        ResponseStatus resp = new ResponseStatus();
         String Response = api.Delete("pemain", id,Username,token);
         
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
     public ResponsepemainById getid(int id){
        ResponsepemainById resp = new ResponsepemainById();
         String Response = api.GetByid("pemain", id);
         
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
             resp = gson.fromJson(Response.toString(), ResponsepemainById.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponsepemainById resp1 = gson.fromJson(Response.toString(), ResponsepemainById.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setData(resp1.getData());
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
    
}

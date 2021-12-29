/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import API.ApiConsume;
import ResponseServer.ResponseJadwal;
import ResponseServer.ResponseStatus;
import ResponseServer.ResponsejadwalByid;
import Utils.ConvertDate;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author teguh
 */
public class JadwalDao {
     private static String alamat;
     Gson gson = new Gson();
     ApiConsume api = new ApiConsume();
    public ResponseJadwal GetAllpage(int activepage, String order, String where){
         ResponseJadwal resp = new ResponseJadwal();
         String Response = api.GetAllpage(activepage, order, where, "jadwal");
         
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
    public ResponseJadwal GetAll(){
         ResponseJadwal resp = new ResponseJadwal();
         String Response = api.GetAll("jadwal");
         
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
    public ResponseStatus Delete(int id ,String Username, String token){
        ResponseStatus resp = new ResponseStatus();
         String Response = api.Delete("jadwal", id,Username,token);
         
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
    public ResponseStatus savejadwal(String team1, String Team2, String liga, String hari, String pukul, String tempat, String status, String matchday, String username, String token){
        
        ConvertDate convert = new ConvertDate();
        String jadwal = hari+ " " + pukul+":00";
        long jadwalconvert = convert.convertDateToLong(jadwal);
        Map<String, String> jad = new HashMap<>();
        Map<String, Object> req = new HashMap<>();
        
        
        jad.put("team1", team1);
        jad.put("team2", Team2);
        jad.put("goal1", "0");
        jad.put("goal2", "0");
        jad.put("hari", String.valueOf(jadwalconvert));
        jad.put("tempat", tempat);
        jad.put("status", status);
        jad.put("matchday", matchday);
        req.put("data", jad);

    
                String result = api.POST(req, "jadwal?idliga=" + liga , username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
    public ResponsejadwalByid GetJadwalByid(int id, String username, String token){
    
    ResponsejadwalByid resp = new ResponsejadwalByid();
         String Response = api.GetByidtoken("jadwal", id, username, token);
         
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
             resp = gson.fromJson(Response.toString(), ResponsejadwalByid.class);
             if(resp.getStatusCode().equals("02")){
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     } else if(resp.getStatusCode().equals("01")){
        
                         resp.setStatusCode(resp.getStatusCode());
                         resp.setStatus(resp.getStatus());
                         
                     }else {
                         ResponsejadwalByid resp1 = gson.fromJson(Response.toString(), ResponsejadwalByid.class);
                         resp.setStatusCode(resp1.getStatusCode());
                         resp.setStatus(resp1.getStatus());
                         resp.setData(resp1.getData());
                 
                     
                     }
             break;
                 
         }
       
        
        return resp;
    
    
    }
    public ResponseStatus editadwal(String id,String team1, String Team2, String liga, String hari, String pukul, String tempat, String status, String matchday, String username, String token){
        
        ConvertDate convert = new ConvertDate();
        String jadwal = hari+ " " + pukul+":00";
        long jadwalconvert = convert.convertDateToLong(jadwal);
        Map<String, String> jad = new HashMap<>();
        Map<String, Object> req = new HashMap<>();
        
        
        jad.put("team1", team1);
        jad.put("team2", Team2);
        jad.put("goal1", "0");
        jad.put("goal2", "0");
        jad.put("hari", String.valueOf(jadwalconvert));
        jad.put("tempat", tempat);
        jad.put("status", status);
        jad.put("matchday", matchday);
        req.put("data", jad);

    
                  String result = api.PUTNomedia(req, "jadwal/"+id+"?idliga=" + liga , username, token);
                  ResponseStatus resp = new ResponseStatus();
                  Gson gson = new Gson();
                  resp = gson.fromJson(result.toString(), ResponseStatus.class);
                  
                  return resp;
    }
}

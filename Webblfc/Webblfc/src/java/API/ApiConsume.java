/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import ResponseServer.ResponseNews;
import Utils.Constants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
public class ApiConsume {
    private static String alamat;
    Gson gson = new Gson();
    public String GetAllpage(int activepage, String order,String where, String url ){
        
         try{
            
          alamat = Constants.BaseUrl+ url;
          alamat += "?activePage={0}&order={1}&where={2}";
          String alamatparam = MessageFormat.format(alamat, new Object[]{activepage,order,where});
         
          URL obj = new URL(alamatparam) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request Header
        con.setRequestProperty("Content-Type", "application/json");
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + alamatparam );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
  
    }
    public String GetByid(String url, int id ){
    
         try{
            
          alamat = Constants.BaseUrl+ url +"/"+id;
     
         
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request Header
        con.setRequestProperty("Content-Type", "application/json");
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    }
    public String GetByidtoken(String url, int id, String username, String token ){
    
         try{
            
          alamat = Constants.BaseUrl+ url +"/"+id;
     
         
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request Header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("username", username);
         con.setRequestProperty("token", token);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    }
    public String GetAll(String url){
        
         try{
            
          alamat = Constants.BaseUrl+ url;
         
         
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request Header
        con.setRequestProperty("Content-Type", "application/json");
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
  
    }  
    public String GetLiga(String url,int id){
        
         try{
            
          alamat = Constants.BaseUrl+ url+"/"+id;
         
         
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        //add request Header
        con.setRequestProperty("Content-Type", "application/json");
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
  
    }
    public String POST(Map<String,Object> post, String url, String username, String token){
    
           try{
            
          alamat = Constants.BaseUrl+ url;
         
          String json = gson.toJson(post);
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         // optional default is GET
         con.setRequestMethod("POST");
         //add request header
         con.setRequestProperty("Content-Type", "application/json");
         con.setRequestProperty("username", username);
         con.setRequestProperty("token", token);
         con.setDoOutput(true);
         OutputStream os = con.getOutputStream();
         OutputStreamWriter wr = new OutputStreamWriter(os, "UTF-8"); 
         System.out.println("API.ApiConsume.POST()" + json);
         wr.write(json);
         wr.flush();
         wr.close();
         os.close();
         con.connect();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
    
    }
    public String POSTMedia(Map<String, String> req,String path,String url , String username, String token){
    
           try{
            
          alamat = Constants.BaseUrl+ url;
         
          // Set header
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("token", token);
            HttpPostMultipart multipart = new HttpPostMultipart(alamat, "utf-8", headers,"POST");
            // Add form field
            
            for ( String key : req.keySet() ) {
              multipart.addFormField(key,req.get(key));
            }

           
            multipart.addFilePart("file", new File(path));
            
           
            // Print result
            String response = multipart.finish();
             System.out.println("\nSending 'POST' request to URL : " + alamat );
             return response;
         }catch(MalformedURLException e){
                e.printStackTrace();
             return "URLException";
         
                
            }
            catch (IOException e) {   
                e.printStackTrace();
            return "IOException";
             
            }
    
    
    } 
    public String PUTMedia(Map<String, String> req, String path,String url , String username, String token){
    
           try{
            
          alamat = Constants.BaseUrl+ url;
         
          // Set header
            Map<String, String> headers = new HashMap<>();
            headers.put("username", username);
            headers.put("token", token);
            HttpPostMultipart multipart = new HttpPostMultipart(alamat, "utf-8", headers,"PUT");
            
           for ( String key : req.keySet() ) {
              multipart.addFormField(key,req.get(key));
            }

           
            multipart.addFilePart("file", new File(path));
            
            
            // Add form field
//            multipart.addFilePart("file", file);
            
           
            // Print result
            String response = multipart.finish();
            
                    System.out.println("\nSending 'PUT' request to URL : " + alamat );

   
             return response;
         }catch(MalformedURLException e){
                e.printStackTrace();
             return "URLException";
         
                
            }
            catch (IOException e) {   
                e.printStackTrace();
            return "IOException";
             
            }
    
    
    } 
    public String PUTNomedia(Map<String,Object> post, String url,String username, String token){
    
           try{
            
          alamat = Constants.BaseUrl+ url;
         
          String json = gson.toJson(post);
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         // optional default is GET
         con.setRequestMethod("PUT");
         //add request header
         con.setRequestProperty("Content-Type", "application/json");
         con.setRequestProperty("username", username);
         con.setRequestProperty("token", token);
         con.setDoOutput(true);
         OutputStream os = con.getOutputStream();
         OutputStreamWriter wr = new OutputStreamWriter(os, "UTF-8"); 
         System.out.println("API.ApiConsume.PUT() no media" + json);
         wr.write(json);
         wr.flush();
         wr.close();
         os.close();
         con.connect();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'PUT' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
    
    }
    public String Delete(String url, int id, String username, String token ){
    
         try{
            
          alamat = Constants.BaseUrl+ url +"/"+id;
     
         
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("DELETE");
        //add request Header
         con.setRequestProperty("Content-Type", "application/json");
         con.setRequestProperty("username", username);
         con.setRequestProperty("token", token);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'DELETE' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    }
    public String POSTLogin(Map<String,Object> post, String url){
    
           try{
            
          alamat = Constants.BaseUrl+ url;
         
          String json = gson.toJson(post);
          URL obj = new URL(alamat) ;
          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         // optional default is GET
         con.setRequestMethod("POST");
         //add request header
         con.setRequestProperty("Content-Type", "application/json");
         con.setDoOutput(true);
         OutputStream os = con.getOutputStream();
         OutputStreamWriter wr = new OutputStreamWriter(os, "UTF-8"); 
         System.out.println("API.ApiConsume.POST()" + json);
         wr.write(json);
         wr.flush();
         wr.close();
         os.close();
         con.connect();
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + alamat );
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (con.getResponseCode() >= 400) {
//                con.getErrorStream();
            BufferedReader out = new BufferedReader(
            new InputStreamReader(con.getErrorStream()));
            String inputLine;

            while ((inputLine = out.readLine()) != null) {
                response.append(inputLine);
            }
            out.close();
            //print in String
            System.out.println("Message : " + response.toString());

        } else {
            /* error from server */

//            con.getInputStream();

            BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            System.out.println("Message : " + response.toString());
           
            
                }
             return response.toString();
             
         }catch(MalformedURLException e){
                
             return "URLException";
         
                
            }
            catch (IOException e) {   
       
            return "IOException";
             
            }
    
    
    }

}

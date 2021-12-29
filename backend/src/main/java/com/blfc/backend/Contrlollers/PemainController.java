package com.blfc.backend.Contrlollers;

import com.blfc.backend.Models.Pemain;
import com.blfc.backend.Service.PemainService;
import com.blfc.backend.Utils.CheckUtil;
import com.blfc.backend.Utils.Constants;
import com.blfc.backend.Utils.GeneratorJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class PemainController {

    @Autowired
    private PemainService pemainService;

    @GetMapping("/pemain")
    public Map<String, Object> getallpemain(

    ){

        Map<String, Object> response = new HashMap<String, Object>();
        List<Pemain> pemainList = pemainService.getAll();

        if(pemainList.size()>0){
            response.put(Constants.LIST, pemainList);
            response.put(Constants.STATUS, Constants.STATUS_DATAFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }else{

            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
        }

        return response;

    }

    @GetMapping("/pemain/{id}")
    public Map<String,Object> GetByid(

            @PathVariable(value = "id") final Integer id
    ){
        Map<String, Object> response = new HashMap<>();
        Pemain mpemain = new Pemain();

        mpemain = pemainService.getById(id);


        if(mpemain != null){

            response.put(Constants.DATA_KEY, mpemain);
            response.put(Constants.STATUS, Constants.SUCCESS);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


        }else{
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

        }



        return response;
    }

    @PostMapping("/pemain")
    public Map<String, Object> saveliga(
            @RequestParam("nama")  String nama,
            @RequestParam("fakultas")  String fakultas,
            @RequestParam("nopunggung")  int nopunggung,
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

        oke= pemainService.savepemain(nama,fakultas,nopunggung,file);

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

    @PutMapping("/pemain")
    public Map<String, Object> updateliga(
            @RequestParam("idpemain")  int idpemain,
            @RequestParam("nama")  String nama,
            @RequestParam("fakultas")  String fakultas,
            @RequestParam("nopunggung")  int nopunggung,
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

        oke= pemainService.updatepemain(idpemain,nama,fakultas,nopunggung,file);

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

    @PutMapping("/pemain/{id}")
    public Map<String, Object> updateliganomedia(
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
        Pemain mpemain = new Pemain();
        Gson gson = new Gson();
        Map<String,Object> pemainmap = (Map<String, Object>) Request.get(Constants.DATA_KEY);

        String jsonString = new ObjectMapper().writeValueAsString(pemainmap);
        System.out.println("ini Response :" + gson.toJson(pemainmap));
        mpemain = gson.fromJson(jsonString, Pemain.class);

        int oke ;

        oke= pemainService.updatepemainomedia(id,mpemain.getNama(), mpemain.getFakultas(), mpemain.getNopunggung(),mpemain.getImage());



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

    //Delete Liga
    @DeleteMapping("/pemain/{id}")
    public Map<String, Object> DeleteLiga (
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
        new Pemain();
        Pemain cekid;
        cekid = pemainService.getById(id);

        if (cekid != null) {
            oke = pemainService.deletepemain(id);
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

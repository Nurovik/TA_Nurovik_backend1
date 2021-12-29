package com.blfc.backend.Contrlollers;
import com.blfc.backend.Models.Klasmen;
import com.blfc.backend.Service.KlasmenService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class KlasmenController {

    @Autowired
    private KlasmenService klasmenService;


    @GetMapping("/klasmen")
    public Map<String,Object> getklasmen(


    ){

        List<Klasmen> lisklasmen = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        lisklasmen = klasmenService.getAll();

        if(lisklasmen.size() > 0){

            response.put(Constants.LIST, lisklasmen);
            response.put(Constants.STATUS, Constants.SUCCESS);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


        }else{
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }


        return response;
    }


    @PostMapping("/klasmen")
    public Map<String, Object> saveliga(
            @RequestParam("namaliga")  String namaliga,
            @RequestParam("detail")  String detail,
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

        oke= klasmenService.saveklasmen(namaliga,detail,file);

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

    @GetMapping("/klasmen/{id}")
    public Map<String, Object> getbyid(
            @PathVariable("id") final Integer id
    ){
        Map<String, Object> response = new HashMap<String, Object>();

        Klasmen Mklasmen = new Klasmen();
        Mklasmen = klasmenService.getById(id);

        if(Mklasmen != null){
            response.put(Constants.DATA_KEY, Mklasmen);
            response.put(Constants.STATUS, Constants.STATUS_DATAFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }else{
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }


        return response;


    }

    @PutMapping("/klasmen")
    public Map<String, Object> updateliga(
            @RequestParam("idklasmen")  int idklasmen,
            @RequestParam("namaliga")  String namaliga,
            @RequestParam("detail")  String detail,
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

        oke= klasmenService.updateklasmen(idklasmen,namaliga,detail,file);

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

    @PutMapping("/klasmen/{id}")
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
        Klasmen mklasmen = new Klasmen();
        Gson gson = new Gson();
        Map<String,Object> Ligamap = (Map<String, Object>) Request.get(Constants.DATA_KEY);

        String jsonString = new ObjectMapper().writeValueAsString(Ligamap);
        mklasmen = gson.fromJson(jsonString, Klasmen.class);

        int oke ;

        oke= klasmenService.updateklasmenomedia(id,mklasmen.getNamaliga(),mklasmen.getDetail(), mklasmen.getGambar());



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

    //Delete klasmen
    @DeleteMapping("/klasmen/{id}")
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
        new Klasmen();
        Klasmen cekid;
        cekid = klasmenService.getById(id);

        if (cekid != null) {
            oke = klasmenService.deleteklasmen(id);
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

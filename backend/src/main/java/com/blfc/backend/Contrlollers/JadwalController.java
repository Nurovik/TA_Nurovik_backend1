package com.blfc.backend.Contrlollers;
import com.blfc.backend.Models.Jadwal;
import com.blfc.backend.Service.JadwalService;
import com.blfc.backend.Utils.CheckUtil;
import com.blfc.backend.Utils.Constants;
import com.blfc.backend.Utils.GeneratorJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/blfc")
public class JadwalController {

    @Autowired
    JadwalService jadwalService;


    @GetMapping("/jadwal")
    public HashMap<String, Object> Getall(

            @RequestParam(required = false) Map<String, String> params

    ){
        HashMap<String, Object> response = new HashMap<>();
        int activePage = NumberUtils.toInt(params.get(Constants.ACTIVE_PAGE));
        int start = (activePage - 1) * Constants.GRIDBOX_MAX_ROW_PER_PAGE_jadwal;
        int limit = Constants.GRIDBOX_MAX_ROW_PER_PAGE_jadwal;
        String orderBy = params.get(Constants.ORDER);
        Map<String, String> assocParams = params;

        List<Jadwal> listjadwal = jadwalService.Getall(start, limit, orderBy, assocParams);

        int total = jadwalService.count();
        if(listjadwal.size()>0){

            response.put(Constants.LIST, listjadwal);
            response.put(Constants.STATUS, Constants.SUCCESS);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            response.put(Constants.TOTAL, total);

        }else{
            response.put(Constants.STATUS, Constants.FAILED);
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


        }



        return response;
    }

    //Function Get jadwal By id
    @GetMapping("/jadwal/{id}")
    public Map<String, Object>  GetById(@PathVariable("id") final int id,
                                        @RequestHeader (required = false, value = "username") String username,
                                        @RequestHeader(required = false, value = "token") String token

    ) {



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


            //Service Karyawan by Id
//          Karyawan Karyawanbyid = new Karyawan(); //Panggil Object Karyawan
            Jadwal jadwal = new Jadwal();
            jadwal = jadwalService.getById(id);

            if(jadwal != null){

                response.put(Constants.DATA_KEY, jadwal);
                response.put(Constants.STATUS,Constants.STATUS_DATAFOUND);
                response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

            }else{
                response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

            }


            //sampai sini service karyawan by id


        }catch (Exception e){

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            e.printStackTrace();
        }


        return response;
    }

    //Insert jadwal
    @PostMapping("/jadwal")
    public Map<String, Object> createJadwal (
            @RequestHeader(required = false, value = "username") String username,
            @RequestHeader(required = false, value = "token") String token,
            @RequestParam("idliga")  int idliga,
            @RequestBody  final Map<String, Object>Request

    )  {


//      checkToken = auntJwt.checkToken(username,token);
        Map<String,Object> response = new HashMap<String,Object>();



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

            int oke = 0;

            Jadwal jd = new Jadwal();
            Gson gson = new Gson();
            Map<String,Object> jadwalmap = (Map<String, Object>) Request.get(Constants.DATA_KEY);


            //CONVERT OBJECT TO JSON STRING
            String jsonString = new ObjectMapper().writeValueAsString(jadwalmap);
            jd = gson.fromJson(jsonString, Jadwal.class);
            if ( jd  !=null){


                oke = jadwalService.savejadwal(jd,idliga);

                if (oke > 0) {
                    //  message = "Data Berhasil diInput";
                    response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSSAVED);
                    response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


                } else {
                    response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSSAVED);
                    response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

                }

            }else{
                response.put(Constants.STATUS, Constants.STATUS_DATA_FAILED);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }



        }catch (Exception e){

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            e.printStackTrace();

        }


        return  response;

    }

//     Edit Jadwal
    @PutMapping("/jadwal/{id}")
    public Map<String, Object> EditJadwal(
            @RequestHeader(required = false, value = "username")String username,
            @RequestHeader(required =  false, value = "token") String token,
            @PathVariable(value = "id") final Integer id,
            @RequestParam("idliga")  int idliga,
            @RequestBody final Map<String,Object>Request

    ) throws IOException {

        Map<String,Object> response = new HashMap<String,Object>();

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

            Jadwal jd = new Jadwal();
            Gson gson = new Gson();


            Map<String,Object> jadwalmap = (Map<String, Object>) Request.get(Constants.DATA_KEY);

            String jsonString = new ObjectMapper().writeValueAsString(jadwalmap);
            System.out.println("ini Response :" + gson.toJson(jadwalmap));
            jd = gson.fromJson(jsonString, Jadwal.class);


            int oke = 0;

            new Jadwal();
            Jadwal  cekid;

            cekid = jadwalService.getById(id);

            if(cekid != null){
                if(jd !=null){

                    oke = jadwalService.updatejadwal(jd,idliga,id);

                    if (oke > 0) {
                        // message = "Data Berhasil dirubah";
                        response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSEDIT);
                        response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


                        //response.put("message", "member deleted successfully");

                    } else {

                        response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                        response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSEDIT);


                    }

                }else{
                    response.put(Constants.STATUS, Constants.STATUS_DATA_FAILED);
                    response.put(Constants.STATUS, Constants.FAILED);


                }

            }else{

                response.put(Constants.STATUS, Constants.STATUS_IDFAILED);
                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);


            }


            System.out.println();


        }catch(Exception e){

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);

            e.printStackTrace();

        }
        return  response;

    }

    //Delete Karyawan
    @DeleteMapping("/jadwal/{id}")
    public Map<String, Object> DeleteJadwal (
            @PathVariable("id") final Integer id,
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

            int oke=0;
            new Jadwal();
            Jadwal  cekid;
            cekid = jadwalService.getById(id);

            if(cekid != null){
                oke = jadwalService.delete(id);
                if(oke>0){
                    response.put(Constants.STATUS, Constants.STATUS_DATASUCCESSDELETE);
                    response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

                }else{
                    response.put(Constants.STATUS, Constants.STATUS_DATAUNSUCCESSDELETE);
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

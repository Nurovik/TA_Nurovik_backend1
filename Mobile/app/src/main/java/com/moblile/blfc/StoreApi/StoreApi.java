package com.moblile.blfc.StoreApi;




import com.moblile.blfc.ResponseServer.ResponseJadwal;
import com.moblile.blfc.ResponseServer.ResponseKlasmen;
import com.moblile.blfc.ResponseServer.ResponseNews;
import com.moblile.blfc.ResponseServer.ResponseNewsDetail;
import com.moblile.blfc.ResponseServer.ResponsePemain;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

;

public interface StoreApi {

    // Enkripsi
//    @POST("autentikasi/enkripsi")
//    Call<ResponseAES> enkripsi(@Body Map<String, Object> request,
//                               @Header("token") String token,
//                               @Header("username") String user);

//    @POST("login")
//    Call<ResponseLogin> doLogin(@Body Map<String, Object> request);

    @GET("news")
    Call<ResponseNews> getDataListnews();

    @GET("news/{id}")
    Call<ResponseNewsDetail> getNewsDetail(@Path("id") String id);


    @GET("klasmen")
    Call<ResponseKlasmen> getDataListklasmen();


    @GET("pemain")
    Call<ResponsePemain> getDataListPemain();

    @GET("jadwal")
    Call<ResponseJadwal> getDataListJadwal();

//    @POST("requestbahan")
//    Call<ResponseServer> send(@Body Map<String, Object> request,
//                              @Header("token") String token,
//                              @Header("username") String user);
//
//
//    @GET("proyek/{id}")
//    Call<ResponseRequest> getlistrequest(@Path("id") String id,
//                                         @Header("token") String token,
//                                         @Header("username") String user);
//
//
//    //Getstatusrequest
//    @GET("requestbahan/{id}")
//    Call<ResponseStatusrequest> getstatusrequest(
//            @Path("id") String id,
//            @Header("token") String token,
//            @Header("username") String user);
//
//
//
//    @DELETE("requestbahan/{id}")
//    Call<ResponseServer> Deleterequest(
//            @Path("id") String id,
//            @Header("token") String token,
//            @Header("username") String user);
//
//
//    @PUT("requestbahan/{id}")
//    Call<ResponseServer> UpdateRequest(
//            @Body Map<String, Object> request,
//            @Header("token") String token,
//            @Header("username") String user,
//            @Path("id") String id);
}

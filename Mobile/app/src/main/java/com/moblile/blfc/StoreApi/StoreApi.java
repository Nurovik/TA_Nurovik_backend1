package com.moblile.blfc.StoreApi;
import com.moblile.blfc.ResponseServer.ResponseJadwal;
import com.moblile.blfc.ResponseServer.ResponseKlasmen;
import com.moblile.blfc.ResponseServer.ResponseLogin;
import com.moblile.blfc.ResponseServer.ResponseNews;
import com.moblile.blfc.ResponseServer.ResponseNewsDetail;
import com.moblile.blfc.ResponseServer.ResponsePemain;
import com.moblile.blfc.ResponseServer.ResponseServer;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface StoreApi {


    @POST("login")
    Call<ResponseLogin> doLogin(@Body Map<String, Object> request);

    @GET("news?where=statuspublish=1")
    Call<ResponseNews> getDataListnews(


    );

    @GET("news/{id}")
    Call<ResponseNewsDetail> getNewsDetail(@Path("id") String id);


    @GET("klasmen")
    Call<ResponseKlasmen> getDataListklasmen();


    @GET("pemain")
    Call<ResponsePemain> getDataListPemain();

    @GET("jadwal")
    Call<ResponseJadwal> getDataListJadwal();


        @DELETE("jadwal/{id}")
    Call<ResponseServer> Deleterequest(
            @Path("id") String id,
            @Header("token") String token,
            @Header("username") String user);


    @PUT("jadwal/{id}")
    Call<ResponseServer> UpdateRequest(
            @Body Map<String, Object> request,
            @Header("token") String token,
            @Header("username") String user,
            @Path("id") String id,
            @Query("idliga") String idliga


    );
}

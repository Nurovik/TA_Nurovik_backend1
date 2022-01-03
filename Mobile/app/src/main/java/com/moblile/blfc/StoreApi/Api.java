package com.moblile.blfc.StoreApi;

import android.util.Log;
import com.moblile.blfc.ResponseServer.ResponseJadwal;
import com.moblile.blfc.ResponseServer.ResponseKlasmen;
import com.moblile.blfc.ResponseServer.ResponseNews;
import com.moblile.blfc.ResponseServer.ResponseNewsDetail;
import com.moblile.blfc.ResponseServer.ResponsePemain;
import com.moblile.blfc.Util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    //Function get request News
    public void getnews( Callback<ResponseNews> callback){

        String baseURL = Utils.BaseUrl;
        Log.e("URL", "ambilData: " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoreApi service = retrofit.create(StoreApi.class);


        Call<ResponseNews> result = service.getDataListnews();
        result.enqueue(callback);


    }

    //Function get request News Detail
    public void getrequestNesDetail(String id, Callback<ResponseNewsDetail> callback){

        String baseURL = Utils.BaseUrl;
        Log.e("URL", "ambilData: " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoreApi service = retrofit.create(StoreApi.class);
        final Call<ResponseNewsDetail> result = service.getNewsDetail(id);
        result.enqueue(callback);


    }


    //Function get request klasmen
    public void klasmen( Callback<ResponseKlasmen> callback){

        String baseURL = Utils.BaseUrl;
        Log.e("URL", "ambilData: " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoreApi service = retrofit.create(StoreApi.class);


        Call<ResponseKlasmen> result = service.getDataListklasmen();
        result.enqueue(callback);


    }

    //Function get request Pemain
    public void pemain( Callback<ResponsePemain> callback){

        String baseURL = Utils.BaseUrl;
        Log.e("URL", "ambilData: " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoreApi service = retrofit.create(StoreApi.class);


        Call<ResponsePemain> result = service.getDataListPemain();
        result.enqueue(callback);


    }


    //Function get request Jadwal
    public void jadwal( Callback<ResponseJadwal> callback){

        String baseURL = Utils.BaseUrl;
        Log.e("URL", "ambilData: " +baseURL );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StoreApi service = retrofit.create(StoreApi.class);


        Call<ResponseJadwal> result = service.getDataListJadwal();
        result.enqueue(callback);


    }

//    //Function Login
//    public void loginapi(String URL, String username, String password, Callback<ResponseLogin> callback){
//
//        String baseURL = "http://" + URL + "/API/sumberbarokah/";
//        Log.e("URL", "ambilData: " +baseURL );
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        StoreApi service = retrofit.create(StoreApi.class);
//        final Map<String, String> isiData = new HashMap<String, String>();
//        isiData.put("usr_username", username);
//        isiData.put("usr_password", password);
//
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("data", isiData);
//
//
//        final Call<ResponseLogin> result = service.doLogin(data);
//        result.enqueue(callback);
//
//    }
//
//    //Function Getall Project
//    public void getallproyek(String URL, String user, String token, Callback<ResponseProyek> callback){
//
//        String baseURL = "http://" + URL + "/API/sumberbarokah/";
//        Log.e("URL", "ambilData: " +baseURL );
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        StoreApi service = retrofit.create(StoreApi.class);
//        final Call<ResponseProyek> result = service.getDataListProyek(token, user);
//        result.enqueue(callback);
//
//
//    }
//
//
//

//
//
//
//    //Function Get statusRequest
//    public void statusrequst(String URL, String user, String token, String id, Callback<ResponseStatusrequest> callback){
//
//        String baseURL = "http://" + URL + "/API/sumberbarokah/";
//        Log.e("URL", "ambilData: " +baseURL );
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        StoreApi service = retrofit.create(StoreApi.class);
//        final Call<ResponseStatusrequest> result = service.getstatusrequest(id, token, user);
//        result.enqueue(callback);
//
//
//    }
//
//
//    //Function Delete Request
//
//    public void Deleterequest (String URL, String user, String token, String id, Callback<ResponseServer> callback){
//        String baseURL = "http://" + URL + "/API/sumberbarokah/";
//        Log.e("URL", "ambilData: " +baseURL );
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        StoreApi service = retrofit.create(StoreApi.class);
//        final Call<ResponseServer> result = service.Deleterequest(id, token, user);
//        result.enqueue(callback);
//
//    }
//
//    public void UpdateRequest(Requestbahan req, String URL, String user, String token, String id, Callback<ResponseServer> callback){
//        String baseURL = "http://" + URL + "/API/sumberbarokah/";
//        Log.e("URL", "ambilData: " +baseURL );
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//        StoreApi service = retrofit.create(StoreApi.class);
//
//        final Map<String, Object> mapData = new HashMap<String, Object>();
//        mapData.put("data", req);
//
//        Call<ResponseServer> result = service.UpdateRequest(mapData, token, user,id);
//        result.enqueue(callback);
//
//    }


}

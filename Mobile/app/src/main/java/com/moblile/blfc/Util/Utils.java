package com.moblile.blfc.Util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Utils {

    public static final String BaseUrlcms= "http://192.168.8.121:4000/news/image/";
    public static final String BaseUrl= "http://192.168.8.121:4000/api/v1/blfc/";


    private static Gson gson;

    public static Gson getGsonParser() {
        if(null == gson) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }

}

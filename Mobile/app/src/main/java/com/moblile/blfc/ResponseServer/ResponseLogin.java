package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {


    @SerializedName("token")
    private String token;

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("status")
    private String status;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;

public class ResponseServer {
    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("status")
    private String status;

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

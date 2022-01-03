package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;
import com.moblile.blfc.Model.News;

public class ResponseNewsDetail {
    @SerializedName("data")
    private News data;

    @SerializedName("status")
    String status;


    @SerializedName("statusCode")
    String statusCode;

    public News getData() {
        return data;
    }

    public void setData(News data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}

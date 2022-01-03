package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;
import com.moblile.blfc.Model.Pemain;

import java.util.List;

public class ResponsePemain {


    @SerializedName("listData")
    List<Pemain> listData;

    @SerializedName("status")
    String status;

    @SerializedName("statusCode")
    String statusCode;


    public List<Pemain> getListData() {
        return listData;
    }

    public void setListData(List<Pemain> listData) {
        this.listData = listData;
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

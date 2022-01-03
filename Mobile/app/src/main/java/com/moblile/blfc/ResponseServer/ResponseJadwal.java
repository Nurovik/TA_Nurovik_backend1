package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;
import com.moblile.blfc.Model.Jadwal;

import java.util.List;

public class ResponseJadwal {

    @SerializedName("listData")
    List<Jadwal> listData;

    @SerializedName("status")
    String status;

    @SerializedName("statusCode")
    String statusCode;

    public List<Jadwal> getListData() {
        return listData;
    }

    public void setListData(List<Jadwal> listData) {
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

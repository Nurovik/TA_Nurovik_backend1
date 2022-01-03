package com.moblile.blfc.ResponseServer;

import com.google.gson.annotations.SerializedName;
import com.moblile.blfc.Model.News;

import java.util.List;

public class ResponseNews {

    @SerializedName("listData")
    private List<News> listData;


    @SerializedName("status")
    private String status;

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("total")
    private String total;

    public List<News> getListData() {
        return listData;
    }

    public void setListData(List<News> listData) {
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

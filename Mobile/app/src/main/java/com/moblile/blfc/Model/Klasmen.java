package com.moblile.blfc.Model;

import com.google.gson.annotations.SerializedName;

public class Klasmen {

    @SerializedName("id_klasmen")
    private int id_klasmen;

    @SerializedName("namaliga")
    private String namaliga;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("detail")
    private String detail;

    public int getId_klasmen() {
        return id_klasmen;
    }

    public void setId_klasmen(int id_klasmen) {
        this.id_klasmen = id_klasmen;
    }

    public String getNamaliga() {
        return namaliga;
    }

    public void setNamaliga(String namaliga) {
        this.namaliga = namaliga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

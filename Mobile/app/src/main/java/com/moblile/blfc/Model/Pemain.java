package com.moblile.blfc.Model;

import com.google.gson.annotations.SerializedName;

public class Pemain {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("fakultas")
    private String fakultas;

    @SerializedName("nopunggung")
    private int nopunggung;

    @SerializedName("image")
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public int getNopunggung() {
        return nopunggung;
    }

    public void setNopunggung(int nopunggung) {
        this.nopunggung = nopunggung;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

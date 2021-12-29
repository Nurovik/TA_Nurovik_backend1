package com.blfc.backend.Models;

public class Pemain {

    private int id;
    private String nama;
    private String fakultas;
    private int nopunggung;
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

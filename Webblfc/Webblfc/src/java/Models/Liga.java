/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author teguh
 */
public class Liga {
    private int idliga;
    private String namaliga;
    private int jumlahteam;
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getIdliga() {
        return idliga;
    }

    public void setIdliga(int idliga) {
        this.idliga = idliga;
    }

    public String getNamaliga() {
        return namaliga;
    }

    public void setNamaliga(String namaliga) {
        this.namaliga = namaliga;
    }

    public int getJumlahteam() {
        return jumlahteam;
    }

    public void setJumlahteam(int jumlahteam) {
        this.jumlahteam = jumlahteam;
    }
    
    
}

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
public class Jadwal {
    private Integer id; 
    private String team1;
    private String team2;
    private int goal1;
    private int goal2;
    private long hari;
    private String tempat;
    private String status;
    private String namaliga;
    private int matchday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getGoal1() {
        return goal1;
    }

    public void setGoal1(int goal1) {
        this.goal1 = goal1;
    }

    public int getGoal2() {
        return goal2;
    }

    public void setGoal2(int goal2) {
        this.goal2 = goal2;
    }

    public long getHari() {
        return hari;
    }

    public void setHari(long hari) {
        this.hari = hari;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaliga() {
        return namaliga;
    }

    public void setNamaliga(String namaliga) {
        this.namaliga = namaliga;
    }
    
    
}

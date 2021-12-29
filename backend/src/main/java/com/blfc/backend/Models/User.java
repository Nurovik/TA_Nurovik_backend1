package com.blfc.backend.Models;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private int id_user;
    private String username;
    private String password;
    private Long datecreated;
    private Long dateupdate;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Long datecreated) {
        this.datecreated = datecreated;
    }

    public Long getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(Long dateupdate) {
        this.dateupdate = dateupdate;
    }
}

package com.moblile.blfc.Model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id_news")
    private int id_news;

    @SerializedName("title")
    private String title;

    @SerializedName("priview")
    private String priview;

    @SerializedName("content")
    private String content;

    @SerializedName("image")
    private String image;

    @SerializedName("datecreated")
    private Long datecreated;

    @SerializedName("dateupdate")
    private Long dateupdate;

    @SerializedName("user")
    private String user;

    @SerializedName("status")
    private int status;


    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriview() {
        return priview;
    }

    public void setPriview(String priview) {
        this.priview = priview;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

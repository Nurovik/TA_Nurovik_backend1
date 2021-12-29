package com.blfc.backend.Models;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class News {

    private int id_news;
    private String title;
    private String priview;
    private String content;
    private String image;
    private Long datecreated;
    private Long dateupdate;
    private String user;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId_news() {
        return id_news;
    }

    public String getPriview() {
        return priview;
    }

    public void setPriview(String priview) {
        this.priview = priview;
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
}

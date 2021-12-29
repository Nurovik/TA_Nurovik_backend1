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
public class News {
            private int id_news;
            private String title;
            private String priview;
            private String content;
            private String image;
            private long datecreated;
            private long dateupdate;
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

    public long getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(long datecreated) {
        this.datecreated = datecreated;
    }

    public long getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(long dateupdate) {
        this.dateupdate = dateupdate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
            
            
}

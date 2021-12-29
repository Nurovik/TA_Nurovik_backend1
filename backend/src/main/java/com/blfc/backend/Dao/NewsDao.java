package com.blfc.backend.Dao;

import com.blfc.backend.Models.News;

import java.util.List;
import java.util.Map;

public interface NewsDao {

    public List<News> getAll(int start, int limit, String order, Map<String, String> params);
    public int insert(News object, int id_user);
    public int buatIdKode();
    public int count();
    public News getbyid(int id);
    public int update(News object , int id_user);
    public int deletenews(int id_news);


}

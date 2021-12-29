package com.blfc.backend.Service;

import com.blfc.backend.Models.News;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface NewsService {
    public List<News> getAll(int start, int limit, String order, Map<String, String> params);
    public int savenews(String username,String title, String priview, String content, MultipartFile file, int status);
    public int count();
    public News getbyid(int id);
    public int update(int idn_news,String username,String title,String priview, String content, MultipartFile file, int status);
    public int updatenewsnomedia(int idn_news,String username,String title,String priview, String content, String image,int status);
    public int deletenews(int id_news);

}

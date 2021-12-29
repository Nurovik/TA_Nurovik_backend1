package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.NewsDao;
import com.blfc.backend.Dao.UserDao;
import com.blfc.backend.Models.News;
import com.blfc.backend.Models.User;
import com.blfc.backend.Service.NewsService;
import com.blfc.backend.Utils.Constants;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Autowired
    private UserDao userdao;


    private static String UPLOADED_FOLDER = Constants.lokasifoto;

    @Override
    public List<News> getAll(int start, int limit, String order, Map<String, String> params) {

        List<News> news = new ArrayList();
        news = newsDao.getAll(start,limit,order,params);
        return news;
    }

    @Override
    public int savenews(String username,String title,String priview, String content, MultipartFile file, int status) {

        int oke;


            News ns = new News();
            User us = new User();
            us = userdao.getusername(username);
            if(us != null){
                    try {

                        String filename = file.getOriginalFilename();
                        Random rand = new Random();
                        // Generate random integers in range 0 to 999
                        int rand_int1 = rand.nextInt(900000000);
                        byte[] bytes = file.getBytes();
                        Path path = Paths.get(UPLOADED_FOLDER + filename);
                        Files.write(path, bytes);
                        ns.setId_news(newsDao.buatIdKode());
                        ns.setTitle(title);
                        ns.setPriview(priview);
                        ns.setContent(content);
                        ns.setImage(filename);
                        ns.setStatus(status);
                        oke = newsDao.insert(ns, us.getId_user());

                        if (oke == 1) {
                            oke = 1;
                        } else {
                            oke = 0;

                        }

                    }catch (Exception e ){
                        e.printStackTrace();
                        oke = 2;

                    }

            } else{

                oke = 3;

            }
        return oke;
    }

    @Override
    public int count() {
        int banyakData = 0;
        banyakData = newsDao.count();

        return banyakData;
    }

    @Override
    public News getbyid(int id) {
        News Mnews = new News();
        try{
            Mnews = newsDao.getbyid(id);
            return Mnews;
        }catch (Exception e ){

            return null;
        }


    }

    @Override
    public int update(int id_news,String username,String title,String priview, String content, MultipartFile file,int status) {
        int oke;


        News ns = new News();
        User us = new User();
        us = userdao.getusername(username);
        if (us != null) {
            try {

                String filename = file.getOriginalFilename();
                Random rand = new Random();
                // Generate random integers in range 0 to 999
                int rand_int1 = rand.nextInt(900000000);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + filename);
                Files.write(path, bytes);
                ns.setId_news(id_news);
                ns.setTitle(title);
                ns.setPriview(priview);
                ns.setContent(content);
                ns.setImage(filename);
                ns.setStatus(status);
                oke = newsDao.update(ns, us.getId_user());

                if (oke == 1) {
                    oke = 1;
                } else {
                    oke = 0;

                }

            } catch (Exception e) {
                oke = 2;

            }

        } else {

            oke = 3;

        }
        return oke;
    }

    @Override
    public int updatenewsnomedia(int idn_news, String username, String title, String priview, String content, String image,int status) {
        int oke;


        News ns = new News();
        User us = new User();
        us = userdao.getusername(username);
        if (us != null) {

                ns.setId_news(idn_news);
                ns.setTitle(title);
                ns.setPriview(priview);
                ns.setContent(content);
                ns.setImage(image);
                ns.setStatus(status);
                oke = newsDao.update(ns, us.getId_user());

                if (oke == 1) {
                    oke = 1;
                } else {
                    oke = 0;

                }



        } else {

            oke = 3;

        }
        return oke;
    }

    @Override
    public int deletenews(int id_news) {
        int oke = 0;
        oke = newsDao.deletenews(id_news);

        return oke;
    }


}

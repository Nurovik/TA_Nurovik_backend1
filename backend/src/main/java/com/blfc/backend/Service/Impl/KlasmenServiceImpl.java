package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.KlasmenDao;
import com.blfc.backend.Models.Klasmen;
import com.blfc.backend.Service.KlasmenService;
import com.blfc.backend.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service("klasmenService")
public class KlasmenServiceImpl implements KlasmenService {

    @Autowired
    private KlasmenDao klasmenDao;

    private static String UPLOADED_FOLDER = Constants.lokasifoto;


    @Override
    public List<Klasmen> getAll() {
        List<Klasmen> lisklasmen = new ArrayList<>();
        try{
            lisklasmen = klasmenDao.getall();
            return lisklasmen;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Klasmen getById(int id) {
        Klasmen mklasmen = new Klasmen();

        try{

            mklasmen = klasmenDao.getbyid(id);

            return  mklasmen;

        }catch (Exception e){
            return null;

        }
    }

    @Override
    public int saveklasmen(String namaliga, String detail, MultipartFile file) {
        int oke;


        Klasmen mklasmen = new Klasmen();


        try {

            String filename = file.getOriginalFilename();
            Random rand = new Random();
            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(900000000);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            mklasmen.setId_klasmen(klasmenDao.buatIdKode());
            mklasmen.setNamaliga(namaliga);
            mklasmen.setDetail(detail);
            mklasmen.setGambar(filename);
            oke = klasmenDao.insert(mklasmen);

            if (oke == 1) {
                oke = 1;
            } else {
                oke = 0;

            }

        }catch (Exception e ){
            e.printStackTrace();
            oke = 2;

        }


        return oke;
    }

    @Override
    public int updateklasmen(int idklasmen, String namaliga, String detail, MultipartFile file) {
        int oke;


        Klasmen mklasmen = new Klasmen();


        try {

            String filename = file.getOriginalFilename();
            Random rand = new Random();
            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(900000000);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            mklasmen.setNamaliga(namaliga);
            mklasmen.setDetail(detail);
            mklasmen.setGambar(filename);
            oke = klasmenDao.update(mklasmen,idklasmen);

            if (oke == 1) {
                oke = 1;
            } else {
                oke = 0;

            }

        }catch (Exception e ){
            e.printStackTrace();
            oke = 2;

        }


        return oke;
    }

    @Override
    public int updateklasmenomedia(int idklasmen, String namaliga, String detail, String gambar) {
        int oke;


        Klasmen mklasmen= new Klasmen();


        try {

            mklasmen.setId_klasmen(idklasmen);
            mklasmen.setNamaliga(namaliga);
            mklasmen.setDetail(detail);
            mklasmen.setGambar(gambar);
            oke = klasmenDao.update(mklasmen,idklasmen);

            if (oke == 1) {
                oke = 1;
            } else {
                oke = 0;

            }

        }catch (Exception e ){
            e.printStackTrace();
            oke = 2;

        }


        return oke;
    }

    @Override
    public int deleteklasmen(int idklasmen) {
        int oke = 0;
        oke = klasmenDao.deleteklasmen(idklasmen);

        return oke;
    }
}

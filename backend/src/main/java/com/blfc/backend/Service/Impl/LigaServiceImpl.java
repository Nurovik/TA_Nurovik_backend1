package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.LigaDao;
import com.blfc.backend.Models.Liga;
import com.blfc.backend.Service.LigaService;
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

@Service("ligaService")
public class LigaServiceImpl implements LigaService {

    @Autowired
    private LigaDao ligaDao;


        private static String UPLOADED_FOLDER = Constants.lokasifoto;

    @Override
    public List<Liga> getAll() {
        List<Liga> listliga = new ArrayList<>();

        try{

            listliga = ligaDao.getAll();

            return  listliga;

        }catch (Exception e){
            return null;

        }


    }

    @Override
    public Liga getById(int id) {
        Liga mliga = new Liga();

        try{

            mliga = ligaDao.getById(id);

            return  mliga;

        }catch (Exception e){
            return null;

        }
    }

    @Override
    public int saveliga(String namaliga, int jumlahteam, MultipartFile file) {
        int oke;


        Liga mliga = new Liga();


            try {

                String filename = file.getOriginalFilename();
                Random rand = new Random();
                // Generate random integers in range 0 to 999
                int rand_int1 = rand.nextInt(900000000);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + filename);
                Files.write(path, bytes);
                mliga.setIdliga(ligaDao.buatIdKode());
                mliga.setNamaliga(namaliga);
                mliga.setJumlahteam(jumlahteam);
                mliga.setLogo(filename);
                oke = ligaDao.insertliga(mliga);

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
    public int updateliga(int idliga, String namaliga, int jumlahteam,  MultipartFile file) {
        int oke;


        Liga mliga = new Liga();


        try {

            String filename = file.getOriginalFilename();
            Random rand = new Random();
            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(900000000);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            mliga.setIdliga(idliga);
            mliga.setNamaliga(namaliga);
            mliga.setJumlahteam(jumlahteam);
            mliga.setLogo(filename);
            oke = ligaDao.update(mliga);

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
    public int updateligaomedia(int idliga, String namaliga, int jumlahteam, String logo) {
        int oke;


        Liga mliga = new Liga();


        try {

            mliga.setIdliga(idliga);
            mliga.setNamaliga(namaliga);
            mliga.setJumlahteam(jumlahteam);
            mliga.setLogo(logo);
            oke = ligaDao.update(mliga);

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
    public int deleteliga(int id) {
        int oke = 0;
        oke = ligaDao.deleteliga(id);

        return oke;
    }
}

package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.PemainDao;
import com.blfc.backend.Models.Pemain;
import com.blfc.backend.Service.PemainService;
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


@Service("pemainService")
public class PemainServiceImpl implements PemainService {
    @Autowired
    private PemainDao pemaindao;

    private static String UPLOADED_FOLDER = Constants.lokasifoto;

    @Override
    public List<Pemain> getAll() {

        List<Pemain> pemain = new ArrayList();
        pemain = pemaindao.getAll();
        return pemain;
    }

    @Override
    public Pemain getById(int id) {
        Pemain mpemain = new Pemain();
        try{
            mpemain = pemaindao.getbyid(id);

            return mpemain;



        }catch(Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public int savepemain(String nama, String fakultas, int nopunggung, MultipartFile file) {
        int oke;


        Pemain mpemain = new Pemain();


        try {

            String filename = file.getOriginalFilename();
            Random rand = new Random();
            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(900000000);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            mpemain.setId(pemaindao.buatIdKode());
            mpemain.setNama(nama);
            mpemain.setFakultas(fakultas);
            mpemain.setNopunggung(nopunggung);
            mpemain.setImage(filename);
            oke = pemaindao.insert(mpemain);

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
    public int updatepemain(int id, String nama, String fakultas, int nopunggung, MultipartFile file) {
        int oke;


        Pemain mpemain = new Pemain();


        try {

            String filename = file.getOriginalFilename();
            Random rand = new Random();
            // Generate random integers in range 0 to 999
            int rand_int1 = rand.nextInt(900000000);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, bytes);
            mpemain.setNama(nama);
            mpemain.setFakultas(fakultas);
            mpemain.setNopunggung(nopunggung);
            mpemain.setImage(filename);
            oke = pemaindao.update(mpemain,id);

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
    public int updatepemainomedia(int idpemain, String nama, String fakultas, int nopunggung, String foto) {
        int oke;


        Pemain mpemain = new Pemain();


        try {

            mpemain.setNama(nama);
            mpemain.setFakultas(fakultas);
            mpemain.setNopunggung(nopunggung);
            mpemain.setImage(foto);
            oke = pemaindao.update(mpemain,idpemain);

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
    public int deletepemain(int id) {
        int oke = 0;
        oke = pemaindao.deletepemain(id);

        return oke;
    }
}

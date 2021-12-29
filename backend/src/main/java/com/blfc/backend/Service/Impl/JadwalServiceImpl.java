package com.blfc.backend.Service.Impl;

import com.blfc.backend.Dao.JadwalDao;
import com.blfc.backend.Dao.LigaDao;
import com.blfc.backend.Dao.TeamDao;
import com.blfc.backend.Models.Jadwal;
import com.blfc.backend.Models.Team;
import com.blfc.backend.Service.JadwalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("jadwalService")
public class JadwalServiceImpl implements JadwalService {

    @Autowired
    JadwalDao jadwalDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    LigaDao ligaDao;

    @Override
    public List<Jadwal> Getall(int start, int limit, String order, Map<String, String> params) {
        List<Jadwal> listjadwal = new ArrayList<>();
        try {
            listjadwal = jadwalDao.Getall(start,limit,order,params);
            return listjadwal;
        }catch (Exception e){
            return  null;
        }
    }

    @Override
    public int savejadwal(Jadwal object, int idliga) {

       Jadwal jd = new Jadwal();
        int oke = 0;
       jd.setId(jadwalDao.buatIdKode());
       jd.setTeam1(object.getTeam1());
       jd.setTeam2(object.getTeam2());
       jd.setGoal1(object.getGoal1());
       jd.setGoal2(object.getGoal2());
       jd.setHari(object.getHari());
       jd.setTempat(object.getTempat());
       jd.setStatus(object.getStatus());
       jd.setMatchday(object.getMatchday());

       try{
           oke = jadwalDao.savejadwal(jd, idliga);
           return  oke;
       }catch (Exception e){

           return  oke;
       }


    }

    @Override
    public int updatejadwal(Jadwal object,int idliga,  int id_jadwal) {

        Jadwal jd = new Jadwal();
        Team mteam = new Team();
        Team mteam2 = new Team();
        mteam = teamDao.Getbyid(object.getTeam1());
        mteam2 = teamDao.Getbyid(object.getTeam2());
        int oke = 0;

        jd.setTeam1(String.valueOf(mteam.getIdteam()));
        jd.setTeam2(String.valueOf(mteam2.getIdteam()));
        jd.setGoal1(object.getGoal1());
        jd.setGoal2(object.getGoal2());
        jd.setHari(object.getHari());
        jd.setTempat(object.getTempat());
        jd.setStatus(object.getStatus());
//        idliga = ligaDao.getbynamateam(namaliga);
        jd.setMatchday(object.getMatchday());

        try{
            oke = jadwalDao.updatejadwal(jd, idliga, id_jadwal);
            return  oke;
        }catch (Exception e){

            return  oke;
        }
    }

    @Override
    public int delete(int id) {
        int oke = 0;
        oke = jadwalDao.delete(id);

        return oke;
    }

    @Override
    public Jadwal getById(int id) {
        Jadwal jd = new Jadwal();
        jd = jadwalDao.getById(id);

        return jd;

    }

    @Override
    public int count() {
        int oke = 0;
        oke = jadwalDao.count();

        return oke;
    }
}

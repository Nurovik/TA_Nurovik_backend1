package com.blfc.backend.Service;

import com.blfc.backend.Models.Jadwal;

import java.util.List;
import java.util.Map;

public interface JadwalService {

    public List<Jadwal> Getall(int start, int limit, String order, Map<String, String> params);
    public int savejadwal(Jadwal object, int idliga);
    public int updatejadwal(Jadwal object, int idliga, int id_jadwal);
    public int delete(int id);
    public Jadwal getById(int id);
    public int count();
}

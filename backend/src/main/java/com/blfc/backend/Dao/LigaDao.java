package com.blfc.backend.Dao;

import com.blfc.backend.Models.Liga;
import java.util.List;

public interface LigaDao {

    public List<Liga> getAll();
    public Liga getById(int id);
    public int insertliga (Liga object);
    public int buatIdKode();
    public int update(Liga object);
    public int deleteliga(int id_liga);
    public int getbynamaliga(String namaliga);
}

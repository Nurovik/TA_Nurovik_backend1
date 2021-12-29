package com.blfc.backend.Dao;

import com.blfc.backend.Models.Klasmen;


import java.util.List;

public interface KlasmenDao {

    public List<Klasmen> getall();
    public int insert(Klasmen object);
    public int buatIdKode();
    public Klasmen getbyid(int id);
    public int update(Klasmen object , int id_klasmen);
    public int deleteklasmen(int id_klasmen);
}

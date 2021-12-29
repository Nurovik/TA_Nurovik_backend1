package com.blfc.backend.Dao;

import com.blfc.backend.Models.News;
import com.blfc.backend.Models.Pemain;

import java.util.List;


public interface PemainDao {

    public List<Pemain> getAll();
    public int insert(Pemain object);
    public int buatIdKode();
    public Pemain getbyid(int id);
    public int update(Pemain object , int id_pemain);
    public int deletepemain(int id_pemain);
}

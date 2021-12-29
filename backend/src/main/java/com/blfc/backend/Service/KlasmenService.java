package com.blfc.backend.Service;

import com.blfc.backend.Models.Klasmen;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KlasmenService {

    public List<Klasmen> getAll();
    public Klasmen getById(int id);
    public int saveklasmen(String namaliga,String detail, MultipartFile file);
    public int updateklasmen(int idklasmen,String namaliga,String detail, MultipartFile file);
    public int updateklasmenomedia(int idklasmen,String namaliga,String detail, String gambar);
    public int deleteklasmen(int idklasmen);
}

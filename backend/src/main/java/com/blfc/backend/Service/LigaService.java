package com.blfc.backend.Service;

import com.blfc.backend.Models.Liga;
import com.blfc.backend.Models.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LigaService {

    public List<Liga> getAll();
    public Liga getById(int id);
    public int saveliga(String namaliga, int jumlahteam, MultipartFile file);
    public int updateliga(int idliga,String namaliga, int jumlahteam, MultipartFile file);
    public int updateligaomedia(int idliga,String namaliga, int jumlahteam, String logo);
    public int deleteliga(int id);

}

package com.blfc.backend.Service;

import com.blfc.backend.Models.Pemain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PemainService {

    public List<Pemain> getAll();
    public Pemain getById(int id);
    public int savepemain(String nama, String fakultas,int nopunggung, MultipartFile file);
    public int updatepemain(int id,String nama,String fakultas, int nopunggung, MultipartFile file);
    public int updatepemainomedia(int idpemain,String nama, String fakultas,int nopunggung, String foto);
    public int deletepemain(int id);
}

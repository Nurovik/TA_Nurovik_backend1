package com.blfc.backend.Dao.Impl;

import com.blfc.backend.Dao.KlasmenDao;
import com.blfc.backend.Dao.LigaDao;
import com.blfc.backend.Models.Klasmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository("klasmenDao")
public class KlasmenDaoImpl implements KlasmenDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    LigaDao ligaDao;

    private String SQL_GETKLASMEN = "select k.id_klasmen, l.namaliga, k.gambar, k.detail from klasmen k LEFT JOIN liga l ON k.liga_idliga = l.idliga";
    private static final String SQL_INSERT_KLASMEN = "insert klasmen values (?,?,?,?)";
    private static final String SQL_GENERATE_ID = "SELECT IFNULL(MAX(CONVERT(id_klasmen, SIGNED INTEGER)), 0) AS kode FROM klasmen";
    private static final String SQL_UPDATE_KLASMEN  = "update klasmen set liga_idliga=? , gambar=?, detail=? where id_klasmen=?";
    private static final String SQL_DELETE = "DELETE FROM klasmen WHERE id_klasmen = ?";
    private String SELECT_GEBYID="select k.id_klasmen, l.namaliga, k.gambar, k.detail from klasmen k LEFT JOIN liga l ON k.liga_idliga = l.idliga WHERE id_klasmen = ?";

    @Override
    public List<Klasmen> getall() {

        List<Klasmen> lisklasmen= new ArrayList<>();
        lisklasmen = jdbcTemplate.query(SQL_GETKLASMEN , new Object[]{}, new RowMapper<Klasmen>() {
            @Override
            public Klasmen mapRow(ResultSet rs, int rowNum) throws SQLException {
                Klasmen kl = new Klasmen();

              kl.setId_klasmen(rs.getInt("id_klasmen"));
              kl.setNamaliga(rs.getString("namaliga"));
              kl.setGambar(rs.getString("gambar"));
              kl.setDetail(rs.getString("detail"));


                return kl;
            }
        });

        return lisklasmen;
    }

    @Override
    public int insert(Klasmen object) {
        int oke = 0;
        int id_klasmen;
        int liga_idliga;
        String 	gambar, detail;

        id_klasmen = object.getId_klasmen();
        liga_idliga = ligaDao.getbynamaliga(object.getNamaliga());
        gambar =object.getGambar();
        detail = object.getDetail();


        oke = jdbcTemplate.update(SQL_INSERT_KLASMEN, new Object[]{
                id_klasmen,
                liga_idliga,
                gambar,
                detail

        });


        return oke;
    }

    @Override
    public int buatIdKode() {
        int banyakData = jdbcTemplate.queryForObject(SQL_GENERATE_ID,null, int.class);
        int nomorBerikutnya = banyakData + 1;
        int urutan = 0;
        if (banyakData == 0) {
            urutan = 1;
        } else {
            urutan = nomorBerikutnya;
        }

        return urutan;
    }

    @Override
    public Klasmen getbyid(int id) {
        Klasmen mklasmen = new Klasmen();


        mklasmen  = jdbcTemplate.queryForObject(SELECT_GEBYID, new Object[]{id}, new RowMapper<Klasmen>() {
            @Override
            public Klasmen mapRow(ResultSet rs, int rowNum) throws SQLException {
                Klasmen kl = new Klasmen();

                kl.setId_klasmen(rs.getInt("id_klasmen"));
                kl.setNamaliga(rs.getString("namaliga"));
                kl.setGambar(rs.getString("gambar"));
                kl.setDetail(rs.getString("detail"));

                return kl;
            }
        });


        return mklasmen ;
    }

    @Override
    public int update(Klasmen object, int id_klasmen) {
        int oke = 0;
        int liga_idliga;
        String 	gambar, detail;

        liga_idliga = ligaDao.getbynamaliga(object.getNamaliga());
        gambar =object.getGambar();
        detail = object.getDetail();


        oke = jdbcTemplate.update(SQL_UPDATE_KLASMEN, new Object[]{

                liga_idliga,
                gambar,
                detail,
                id_klasmen

        });

        return oke;
    }

    @Override
    public int deleteklasmen(int id_klasmen) {
        int oke = 0;
        oke = jdbcTemplate.update(SQL_DELETE, new Object[]{id_klasmen});
        System.out.print(oke);
        return oke;
    }
}

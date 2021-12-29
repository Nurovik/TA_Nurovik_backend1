package com.blfc.backend.Dao.Impl;
import com.blfc.backend.Dao.LigaDao;
import com.blfc.backend.Models.Liga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("logaDao")
public class LigaDaoImpl implements LigaDao {

    private String SELECT_GETALLTEAM="select * FROM liga";
    private String SELECT_GEBYID="select * FROM liga where idliga = ?";
    private String SELECT_BYNAMALIGA="select idliga from liga WHERE namaliga =?";
    private static final String SQL_INSERT_LIGA = "INSERT INTO liga VALUES(?,?,?,?)";
    private static final String SQL_GENERATE_ID = "SELECT IFNULL(MAX(CONVERT(idliga, SIGNED INTEGER)), 0) AS kode FROM liga";
    private static final String SQL_UPDATE_LIGA  = "UPDATE liga SET namaliga = ?, jumlahteam = ?, logo = ? WHERE idliga = ?";
    private static final String SQL_DELETE = "DELETE FROM liga WHERE idliga = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Liga> getAll() {
        List<Liga> klasmenList = new ArrayList<>();

        klasmenList = jdbcTemplate.query(SELECT_GETALLTEAM, new RowMapper<Liga>() {
            @Override
            public Liga mapRow(ResultSet rs, int rowNum) throws SQLException {

                Liga mliga = new Liga();
                mliga.setIdliga(rs.getInt("idliga"));
                mliga.setNamaliga(rs.getString("namaliga"));
                mliga.setJumlahteam(rs.getInt("jumlahteam"));
                mliga.setLogo(rs.getString("logo"));

                return mliga;
            }
        });


        return klasmenList;
    }

    @Override
    public Liga getById(int id) {
        Liga mliga = new Liga();


        mliga = jdbcTemplate.queryForObject(SELECT_GEBYID, new Object[]{id}, new RowMapper<Liga>() {
            @Override
            public Liga mapRow(ResultSet rs, int rowNum) throws SQLException {
                Liga mliga = new Liga();
                mliga.setIdliga(rs.getInt("idliga"));
                mliga.setNamaliga(rs.getString("namaliga"));
                mliga.setJumlahteam(rs.getInt("jumlahteam"));
                mliga.setLogo(rs.getString("logo"));

                return mliga;
            }
        });


        return mliga;
    }

    @Override
    public int insertliga(Liga object) {
        int oke = 0;
        int idliga,jumlahteam;
        String 	namaliga, logo;

        idliga = object.getIdliga();
        jumlahteam = object.getJumlahteam();
        namaliga =object.getNamaliga();
        logo = object.getLogo();


        oke = jdbcTemplate.update(SQL_INSERT_LIGA, new Object[]{
                idliga,
                namaliga,
                jumlahteam,
                logo

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
    public int update(Liga object) {
        int oke = 0;
        int idliga,jumlahteam;
        String 	namaliga, logo;

        idliga = object.getIdliga();
        jumlahteam = object.getJumlahteam();
        namaliga =object.getNamaliga();
        logo = object.getLogo();



                oke = jdbcTemplate.update(SQL_UPDATE_LIGA, new Object[]{
                        namaliga,
                        jumlahteam,
                        logo,
                        idliga

                });


        return oke;
    }

    @Override
    public int deleteliga(int id_liga) {
        int oke = 0;
        oke = jdbcTemplate.update(SQL_DELETE, new Object[]{id_liga});
        System.out.print(oke);
        return oke;
    }

    @Override
    public int getbynamaliga(String namaliga) {


        try {
            int idteam = jdbcTemplate.queryForObject(SELECT_BYNAMALIGA, new Object[]{namaliga}, int.class);


            return idteam;
        }catch (Exception e){
            e.printStackTrace();
            return 0 ;
        }
    }
}

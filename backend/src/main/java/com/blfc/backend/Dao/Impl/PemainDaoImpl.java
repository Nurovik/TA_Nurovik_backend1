package com.blfc.backend.Dao.Impl;
import com.blfc.backend.Dao.PemainDao;
import com.blfc.backend.Models.Pemain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository("pemainDao")
public class PemainDaoImpl implements PemainDao {


    private static final String SQL_SELECT_ALL_PEMAIN = "select * from pemain";
    private static final String SQL_INSERT_PEMAIN= "INSERT INTO pemain VALUES(?,?,?,?,?,?)";
    private static final String SQL_GENERATE_ID = "SELECT IFNULL(MAX(CONVERT(id, SIGNED INTEGER)), 0) AS kode FROM pemain";
    private static final String SQL_UPDATE_PEMAIN  = "UPDATE pemain SET nama = ?, fakultas = ?, nopunggung = ? , image = ? , team_idteam = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM pemain WHERE id = ?";
    private String SELECT_GEBYID_PEMAIN="select * FROM pemain where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pemain> getAll() {
        List<Pemain> listpemain = null;

        listpemain = jdbcTemplate.query(SQL_SELECT_ALL_PEMAIN, new RowMapper<Pemain>() {
            @Override
            public Pemain mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pemain pm = new Pemain();

                pm.setId(rs.getInt("id"));
                pm.setNama(rs.getString("nama"));
                pm.setFakultas(rs.getString("fakultas"));
                pm.setNopunggung(rs.getInt("nopunggung"));
                pm.setImage(rs.getString("image"));
                return pm;
            }
        });

        return listpemain;
    }

    @Override
    public int insert(Pemain object) {
        int oke = 0;
        int id,nopunggung,idteam;
        String 	nama, fakultas, foto;

        id = object.getId();
        nama = object.getNama();
        fakultas =object.getFakultas();
        nopunggung = object.getNopunggung();
        foto = object.getImage();
        idteam = 1;


        oke = jdbcTemplate.update(SQL_INSERT_PEMAIN, new Object[]{
                id,
                nama,
                fakultas,
                nopunggung,
                foto,
                idteam

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
    public Pemain getbyid(int id) {
        Pemain mpemain = new Pemain();


        mpemain = jdbcTemplate.queryForObject(SELECT_GEBYID_PEMAIN, new Object[]{id}, new RowMapper<Pemain>() {
            @Override
            public Pemain mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pemain mpemain = new Pemain();
                mpemain.setId(rs.getInt("id"));
                mpemain.setNama(rs.getString("nama"));
                mpemain.setFakultas(rs.getString("fakultas"));
                mpemain.setNopunggung(rs.getInt("nopunggung"));
                mpemain.setImage(rs.getString("image"));

                return mpemain;
            }
        });


        return mpemain;
    }

    @Override
    public int update(Pemain object, int id_pemain) {
        int oke = 0;
        int nopunggung,idteam;
        String 	nama, fakultas, foto;


        nama = object.getNama();
        fakultas =object.getFakultas();
        nopunggung = object.getNopunggung();
        foto = object.getImage();
        idteam = 1;


        oke = jdbcTemplate.update(SQL_UPDATE_PEMAIN, new Object[]{

                nama,
                fakultas,
                nopunggung,
                foto,
                idteam,
                id_pemain,

        });


        return oke;
    }

    @Override
    public int deletepemain(int id_pemain) {
        int oke = 0;
        oke = jdbcTemplate.update(SQL_DELETE, new Object[]{id_pemain});
        System.out.print(oke);
        return oke;
    }
}

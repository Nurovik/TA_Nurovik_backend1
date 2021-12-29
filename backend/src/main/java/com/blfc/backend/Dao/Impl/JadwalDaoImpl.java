package com.blfc.backend.Dao.Impl;
import com.blfc.backend.Dao.JadwalDao;
import com.blfc.backend.Models.Jadwal;
import com.blfc.backend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository("jadwalDao")
public class JadwalDaoImpl implements JadwalDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    private String SELECT_ALL_JADWAL = "SELECT j.idjadwal, t.namateam as team1, t2.namateam as team2, j.goal1, j.goal2, j.hari, j.tempat, j.`status`, l.namaliga , j.matchday " +
            "from jadwal j LEFT JOIN liga l ON j.liga_idliga = l.idliga " +
            "LEFT JOIN team t ON t.idteam = j.team1 " +
            "LEFT JOIN team t2 ON t2.idteam = j.team2 WHERE j.flag = 0 and j.team1 =1";

    private String INSERT_JADWAL = "INSERT INTO jadwal VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private String UPDATE_JADWAL = "UPDATE jadwal SET team1 = ?, team2 = ?, goal1 = ?, goal2 = ?, " +
                                    "hari = ?, tempat = ?, status = ?, flag = ?,  liga_idliga = ? ,  matchday = ?  WHERE idjadwal = ?;";
    private static final String SQL_DELETE = "DELETE FROM jadwal WHERE idjadwal = ?";
    private static final String SQL_GENERATE_ID = "SELECT IFNULL(MAX(CONVERT(idjadwal, SIGNED INTEGER)), 0) AS kode FROM jadwal";
    private static final String SQL_JADWAL_BY_ID = "SELECT j.idjadwal, t.namateam AS team1, t2.namateam AS team2, j.goal1, j.goal2, j.hari, j.tempat, j.`status`, l.namaliga, j.matchday" +
            " FROM jadwal j LEFT JOIN liga l ON j.liga_idliga = l.idliga LEFT JOIN team t ON t.idteam = j.team1 LEFT JOIN team t2 ON t2.idteam = j.team2 WHERE j.flag = 0 AND j.idjadwal = ?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM jadwal";




    @Override
    public List<Jadwal> Getall(int start, int limit, String order, Map<String, String> params) {

        List<Jadwal> listjadwal = new ArrayList<>();
        String where = Utils.getClauseWhere(params);
        String orderBy = Utils.getOrderBy(order);
        if(start > -1 && limit >0){
            listjadwal = jdbcTemplate.query(SELECT_ALL_JADWAL + " ORDER BY " + orderBy + " LIMIT ?,?", new Object[]{start, limit}, new RowMapper<Jadwal>() {
                @Override
                public Jadwal mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Jadwal jd = new Jadwal();
                    jd.setId(rs.getInt("idjadwal"));
                    jd.setTeam1(rs.getString("team1"));
                    jd.setTeam2(rs.getString("team2"));
                    jd.setGoal1(rs.getInt("goal1"));
                    jd.setGoal2(rs.getInt("goal2"));
                    jd.setHari(rs.getTimestamp("hari").getTime());
                    jd.setTempat(rs.getString("tempat"));
                    jd.setStatus(rs.getString("status"));
                    jd.setNamaliga(rs.getString("namaliga"));
                    jd.setMatchday(rs.getInt("matchday"));
                    return jd;
                }
            });
        }else {
            listjadwal = jdbcTemplate.query(SELECT_ALL_JADWAL, new Object[]{}, new RowMapper<Jadwal>() {
                @Override
                public Jadwal mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Jadwal jd = new Jadwal();
                    jd.setId(rs.getInt("idjadwal"));
                    jd.setTeam1(rs.getString("team1"));
                    jd.setTeam2(rs.getString("team2"));
                    jd.setGoal1(rs.getInt("goal1"));
                    jd.setGoal2(rs.getInt("goal2"));
                    jd.setHari(rs.getTimestamp("hari").getTime());
                    jd.setTempat(rs.getString("tempat"));
                    jd.setStatus(rs.getString("status"));
                    jd.setNamaliga(rs.getString("namaliga"));
                    jd.setMatchday(rs.getInt("matchday"));
                    return jd;
                }
            });
        }
        return listjadwal;
    }

    @Override
    public int savejadwal(Jadwal object,  int idliga) {
         int oke;
         String team1;
         String team2;
         int goal1;
         int goal2;
         String tempat;
         String status;
         int flag = 0;
         int id, matchday;



         id = object.getId();
         team1 = object.getTeam1();
         team2 = object.getTeam2();
         goal1 = object.getGoal1();
         goal2 = object.getGoal2();
         Date hari = new Date(object.getHari());
         tempat  = object.getTempat();
         status  = object.getStatus();
         matchday = object.getMatchday();


            try {
                oke = jdbcTemplate.update(INSERT_JADWAL, new Object[]{
                        id,
                        team1,
                        team2,
                        goal1,
                        goal2,
                        hari,
                        tempat,
                        status,
                        idliga,
                        flag,
                        matchday
                });



                return oke;
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }

    }

    @Override
    public int updatejadwal(Jadwal object, int idliga, int id_jadwal) {
        int oke;
        String team1;
        String team2;
        int goal1;
        int goal2;

        String tempat;
        String status;
        int flag = 0;
        int matchday;


        team1 = object.getTeam1();
        team2 = object.getTeam2();
        goal1 = object.getGoal1();
        goal2 = object.getGoal2();
        Date hari = new Date(object.getHari());
        tempat  = object.getTempat();
        status  = object.getStatus();
        matchday = object.getMatchday();



        try {
            oke = jdbcTemplate.update(UPDATE_JADWAL, new Object[]{
                    team1,
                    team2,
                    goal1,
                    goal2,
                    hari,
                    tempat,
                    status,
                    flag,
                    idliga,
                    matchday,
                    id_jadwal
            });
            return oke;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }



    }

    @Override
    public int delete(int id) {
        int oke = 0;
        oke = jdbcTemplate.update(SQL_DELETE, new Object[]{id});
        System.out.print(oke);
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
    public Jadwal getById(int id) {
        Jadwal mjadwal = new Jadwal();
        try {

            mjadwal = jdbcTemplate.queryForObject(SQL_JADWAL_BY_ID, new Object[]{id}, new RowMapper<Jadwal>() {
                @Override
                public Jadwal mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Jadwal jd = new Jadwal();
                    jd.setId(rs.getInt("idjadwal"));
                    jd.setTeam1(rs.getString("team1"));
                    jd.setTeam2(rs.getString("team2"));
                    jd.setGoal1(rs.getInt("goal1"));
                    jd.setGoal2(rs.getInt("goal2"));
                    jd.setHari(rs.getTimestamp("hari").getTime());
                    jd.setTempat(rs.getString("tempat"));
                    jd.setStatus(rs.getString("status"));
                    jd.setNamaliga(rs.getString("namaliga"));
                    jd.setMatchday(rs.getInt("matchday"));

                    return jd;
                }
            });


            return mjadwal;
        }catch (Exception e){

            return null;
        }
    }

    @Override
    public int count() {
        int totalData = jdbcTemplate.queryForObject(SQL_COUNT ,null,int.class);
        return totalData;
    }
}

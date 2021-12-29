package com.blfc.backend.Dao.Impl;
import com.blfc.backend.Dao.NewsDao;
import com.blfc.backend.Models.News;
import com.blfc.backend.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository("newsDao")
public class NewsDaoImpl implements NewsDao {

    private static final String SQL_SELECT_ALL_news = "SELECT n.id_news, n.title, n.priview,n.image, u.username,n.statuspublish, n.datecreated, n.dateupdate \n" +
            "from news n  LEFT JOIN `user` u ON  n.user_id_user = u.id_user";

    private static final String SQL_INSERT_NEWS = "INSERT INTO news VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_GENERATE_ID = "SELECT IFNULL(MAX(CONVERT(id_news, SIGNED INTEGER)), 0) AS kode FROM news";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM news";
    private static final String SQL_SELECT_BYID = "SELECT news.id_news, news.title, news.content, news.priview, news.image, news.datecreated,news.dateupdate, news.statuspublish, `user`.username " +
            "FROM news INNER JOIN `user` ON news.user_id_user = `user`.id_user WHERE news.id_news = ?";
    private static final String SQL_UPDATE_NEWS  = "UPDATE news SET title = ?, priview = ?, content = ?, image = ?, dateupdate = ?, user_id_user = ? , statuspublish = ?    WHERE id_news = ?";
    private static final String SQL_DELETE = "DELETE FROM news WHERE id_news = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<News> getAll(int start, int limit, String order, Map<String, String> params) {

        List<News> listnews = null;
        String where = Utils.getClauseWhere(params);
        String orderBy = Utils.getOrderBy(order);

        if(start > -1 && limit >0){

            listnews = jdbcTemplate.query(SQL_SELECT_ALL_news + where + " ORDER BY " + orderBy + " LIMIT ?,?", new Object[]{start, limit}, new RowMapper<News>() {
                @Override
                public News mapRow(ResultSet rs, int rowNum) throws SQLException {
                    News ns = new News();

                    ns.setId_news(rs.getInt("id_news"));
                    ns.setTitle(rs.getString("title"));
                    ns.setPriview(rs.getString("priview"));
                    ns.setImage(rs.getString("image"));
                    ns.setUser(rs.getString("username"));
                    ns.setDatecreated(rs.getTimestamp("datecreated").getTime());
                    ns.setDateupdate(rs.getTimestamp("dateupdate").getTime());
                    ns.setStatus(rs.getInt("statuspublish"));

                    return ns;
                }
            });
        }else{

            listnews = jdbcTemplate.query(SQL_SELECT_ALL_news , new RowMapper<News>() {
                @Override
                public News mapRow(ResultSet rs, int rowNum) throws SQLException {


                    News ns = new News();

                    ns.setId_news(rs.getInt("id_news"));
                    ns.setTitle(rs.getString("title"));
                    ns.setPriview(rs.getString("priview"));
                    ns.setImage(rs.getString("image"));
                    ns.setUser(rs.getString("username"));
                    ns.setDatecreated(rs.getTimestamp("datecreated").getTime());
                    ns.setDateupdate(rs.getTimestamp("dateupdate").getTime());
                    ns.setStatus(rs.getInt("statuspublish"));

                    return ns;
                }
            });


        }
        return listnews;
    }

    @Override
    public int insert(News object,int id_user) {
        int oke = 0;
        int id_news, status;
        String 	title, priview,	content	,image;

        id_news = object.getId_news();
        title = object.getTitle();
        priview =object.getPriview();
        content = object.getContent();
        image = object.getImage();
        status = object.getStatus();

        oke = jdbcTemplate.update(SQL_INSERT_NEWS, new Object[]{
                id_news,
                title,
                priview,
                content,
                image,
                new Date(),
                new Date(),
                id_user,
                status

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
    public int count() {
        int totalData = jdbcTemplate.queryForObject(SQL_COUNT ,null,int.class);
        return totalData;
    }

    @Override
    public News getbyid(int id) {
        News ns = new News();

        try{
            ns = jdbcTemplate.queryForObject(SQL_SELECT_BYID, new Object[]{id}, new RowMapper<News>() {
                @Override
                public News mapRow(ResultSet rs, int rowNum) throws SQLException {
                    News Mnews = new News();

                    Mnews.setId_news(rs.getInt("id_news"));
                    Mnews.setTitle(rs.getString("title"));
                    Mnews.setPriview(rs.getString("priview"));
                    Mnews.setContent(rs.getString("content"));
                    Mnews.setImage(rs.getString("image"));
                    Mnews.setUser(rs.getString("username"));
                    Mnews.setDatecreated(rs.getTimestamp("datecreated").getTime());
                    Mnews.setDateupdate(rs.getTimestamp("dateupdate").getTime());
                    Mnews.setStatus(rs.getInt("statuspublish"));
                    return Mnews;
                }
            });

        return ns;
        }catch (Exception e){

        return null;
        }
    }

    @Override
    public int update(News object, int id_user) {
        int oke = 0;
        int id_news,status;
        String 	title, priview,	content	,image;

        id_news = object.getId_news();
        title = object.getTitle();
        priview =object.getPriview();
        content = object.getContent();
        image = object.getImage();
        status = object.getStatus();

        oke = jdbcTemplate.update(SQL_UPDATE_NEWS, new Object[]{
                title,
                priview,
                content,
                image,
                new Date(),
                id_user,
                status,
                id_news

        });


        return oke;
    }

    @Override
    public int deletenews(int id_news) {
        int oke = 0;
        oke = jdbcTemplate.update(SQL_DELETE, new Object[]{id_news});
        System.out.print(oke);
        return oke;
    }


}

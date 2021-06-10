package com.example.demo.repository.impl;

import com.example.demo.entity.Picture;
import com.example.demo.repository.PictureRep;
import com.example.demo.tool.MyPictureIOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component("pictureRep")
public class PictureRepApl implements PictureRep {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private MyPictureIOS myPictureIOS;

  @Override
  public Long findExistById(Long Id) {
    return jdbcTemplate.queryForObject("select count(*) from picture_list where id = ?", Long.class, Id);
  }

  /**
   *
   * @param Id
   * @return 按Id查找图片，如果不存在则返回null
   */
  @Override
  public Picture findById(Long Id) {
    Long exist = findExistById(Id);
    if(exist!=0){
      Picture picture = jdbcTemplate.queryForObject("select * from picture_list where id = ?",new BeanPropertyRowMapper<Picture>(Picture.class),Id);
      return picture;
    }else
      return null;
  }

  /**
   * 被淘汰了，因为blod和bytes可以自动转换
   * @param picture
   * @return
   * @throws IOException
   */
  @Override
  public Boolean addInfoLocal(Picture picture) throws IOException {
//    System.out.println(tableName);
    Long aLong = jdbcTemplate.queryForObject("select count(*) from picture_list where owner = ? and categy = ? and ispublic = ? and picname = ?", Long.class,picture.getOwner(), picture.getCategy(),picture.getIspublic(),picture.getPicname());
//    System.out.println(aLong);
    if(aLong!=0){
      return false;
    }
    String abspath = myPictureIOS.path+"/"+picture.getOwner()+"/"+picture.getPicname();
    FileInputStream inputStream = myPictureIOS.readImage(abspath);
    byte[] bytes = inputStream.readAllBytes();
    inputStream.close();
    FileInputStream inputStream1 = myPictureIOS.readImage(abspath);
    LobHandler lobHandler = new DefaultLobHandler();
    jdbcTemplate.execute("insert picture_list(owner,categy,ispublic,picname,personTag,ishuman,binarypic) values(?,?,?,?,?,?,?)",
      new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
        @Override
        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
          ps.setString(1, picture.getOwner());
          ps.setString(2, picture.getCategy());
          ps.setBoolean(3, picture.getIspublic());
          ps.setString(4, picture.getPicname());
          ps.setString(5, picture.getPersontag());
          ps.setBoolean(6,picture.getIshuman());
          lobCreator.setBlobAsBinaryStream(ps,7,inputStream1,bytes.length);
        }
      }
    );
    inputStream1.close();
    return true;
  }

  @Override
  public Boolean addInfoBybytes(Picture picture) throws IOException {
    jdbcTemplate.update("insert picture_list(owner,categy,ispublic,picname,personTag,ishuman,binarypic) values(?,?,?,?,?,?,?)",
      picture.getOwner(),picture.getCategy(), picture.getIspublic(),picture.getPicname(),picture.getPersontag(),picture.getIshuman(),picture.getBinarypic());
    return true;
  }

  @Override
  public Boolean deleteById(Long Id) {
    Long exist = findExistById(Id);
    if(exist!=0){
      jdbcTemplate.update("delete from picture_list where id = ? ", Id );
      return true;
    }else
      return false;
  }

  @Override
  public List<Picture> findList(String owner, boolean ispublic, String categy) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from picture_list where owner = ? and ispublic =? and categy = ?",Long.class,owner,ispublic,categy);
    if(exist==0){
      return null;
    }else{
      return jdbcTemplate.query("select * from picture_list where owner = ? and ispublic = ? and categy = ? ", new BeanPropertyRowMapper<Picture>(Picture.class), owner, ispublic, categy);
    }
  }

  @Override
  public void createTable() {
    //自动生成二进制文件,可以用来存储图片的bytes数组。
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS picture_list(\n").append("`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n").append("`owner` VARCHAR(20) NOT NULL,\n").append("`categy` VARCHAR(20) NOT NULL,\n").append("`binarypic` MediumBlob NOT NULL,\n").append("`ispublic` BOOL DEFAULT TRUE,\n").append("`picname` VARCHAR(40) NOT NULL,\n").append("`persontag` VARCHAR(20) DEFAULT NULL,\n").append("`ishuman` BOOLEAN DEFAULT FALSE\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
  }
}

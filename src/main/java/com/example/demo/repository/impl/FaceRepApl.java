package com.example.demo.repository.impl;

import com.example.demo.entity.Face;
import com.example.demo.repository.FaceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("faceRep")
public class FaceRepApl implements FaceRep {
  @Autowired
  JdbcTemplate jdbcTemplate;
  @Override
  public boolean findById(Long Id) {
    return jdbcTemplate.queryForObject("select count(*) from face where faceId = ?",Long.class,Id)>0L;
  }

  @Override
  public boolean insert(Face face) {
    if(!findById(face.getFaceId())){
      return jdbcTemplate.update("insert face(faceId,faceSetId,faceName,number) values(?,?,?,?)",face.getFaceId(),face.getFaceSetId(),face.getFaceName(),face.getNumber())>0;
    }
    return false;
  }

  @Override
  public boolean delete(Long faceId) {
    return jdbcTemplate.update("delete from face where faceId = ?",faceId)>0;
  }

  @Override
  public Long changeNumber(Long faceId, int num) {
    Face face = findByFaceId(faceId);
    Long number = face.getNumber() + (long)num;
    jdbcTemplate.update("update face set number = ? where faceId = ?",number,faceId);
    return number;
  }

  @Override
  public List<Face> findByFaceSetId(Long faceSetId) {
    return jdbcTemplate.query("select * from face where faceSetId = ?",new BeanPropertyRowMapper<Face>(Face.class),faceSetId);
  }

  @Override
  public Face findByFaceId(Long Id) {
    Face face = jdbcTemplate.queryForObject("select * from face where faceId = ?", new BeanPropertyRowMapper<Face>(Face.class), Id);
    return face;
  }

  @Override
  public void createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS face(\n" +
      "`faceId` BIGINT NOT NULL PRIMARY KEY,\n" +
      "`faceSetId` BIGINT NOT NULL,\n" +
      "`faceName` VARCHAR(40) DEFAULT 'nobody',\n" +
      "`number` INT DEFAULT 0\n" +
      ")ENGINE=INNODB DEFAULT CHARSET=utf8;";
    jdbcTemplate.update(sql);
  }
}

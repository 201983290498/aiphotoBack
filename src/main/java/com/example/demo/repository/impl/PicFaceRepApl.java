package com.example.demo.repository.impl;

import com.example.demo.entity.PicFace;
import com.example.demo.repository.PicFaceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("picFaceRep")
public class PicFaceRepApl implements PicFaceRep {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public boolean add(PicFace picFace){
      String sql = "insert pic_face(faceId,picId,ispublic,confidence) values(?,?,?,?)";
      int update = jdbcTemplate.update(sql, picFace.getFaceId(), picFace.getPicId(),picFace.isIspublic(), picFace.getConfident());
      return update>0;
  }

  @Override
  public boolean findExistById(Long Id) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from pic_face where id = ?",Long.class,Id);
    return exist>0L;
  }
  @Override
  public boolean findExistById(Long faceId,Long picId) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from pic_face where faceId = ? and picId = ?",Long.class,faceId,picId);
    return exist!=0L;
  }


  /**
   * 单一值映射只要写一类，否则需要一个类型转换器
   * @param Id
   * @return
   */
  @Override
  public PicFace findById(Long Id) {
    PicFace picFace = null;
    picFace = jdbcTemplate.queryForObject("select * from pic_face where id = ?",new BeanPropertyRowMapper<>(PicFace.class), Id);
    return picFace;
  }

  @Override
  public boolean deleteById(Long Id) {
      return jdbcTemplate.update("delete from pic_face where id = ?",Id)>0;
  }

  @Override
  public List<PicFace> findByFace(Long faceId) {
    List<PicFace> picFaceList = null;
    picFaceList = jdbcTemplate.query("select * from pic_face where faceId = ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),faceId);
    return picFaceList;
  }

  @Override
  public List<PicFace> findByFace(Long faceId, boolean ispublic) {
    List<PicFace> picFaceList = null;
    if(ispublic)
      picFaceList = jdbcTemplate.query("select * from pic_face where faceId = ? and ispublic = ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),faceId,ispublic);
    else{
      picFaceList = findByFace(faceId);
    }
    return picFaceList;
  }

  @Override
  public List<PicFace> findByFace(Long faceId, boolean ispublic,double confidence) {
    List<PicFace> picFaceList = null;
    if(ispublic)//公有照片智能聚合公有的照片
      picFaceList = jdbcTemplate.query("select * from pic_face where faceId = ? and ispublic = ? and confidence >= ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),faceId,ispublic,confidence);
    else//私有照片可以人工搜素任何图片
      picFaceList = jdbcTemplate.query("select * from pic_face where faceId = ? and confidence >= ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),faceId,confidence);
    return picFaceList;
  }

  @Override
  public List<PicFace> findByPic(Long picId) {
    List<PicFace> picFaceList = null;
    picFaceList = jdbcTemplate.query("select * from pic_face where picId = ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),picId);
    return picFaceList;
  }

  @Override
  public List<PicFace> findByPic(Long picId, double confidence) {
    List<PicFace> picFaceList = null;
    picFaceList = jdbcTemplate.query("select * from pic_face where picId = ? and confidence >= ?",new BeanPropertyRowMapper<PicFace>(PicFace.class),picId,confidence);
    return picFaceList;
  }

  @Override
  public void createTable() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CREATE TABLE IF NOT EXISTS pic_face(\n");
    stringBuilder.append("   `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n");
    stringBuilder.append("   `faceId` BIGINT  NOT NULL,\n");
    stringBuilder.append("   `picId` BIGINT NOT NULL,\n");
    stringBuilder.append("   `ispublic` boolean not null,\n");
    stringBuilder.append("   `confidence` double not null\n");
    stringBuilder.append(")ENGINE=INNODB DEFAULT CHARSET=utf8;");
    String sql = stringBuilder.toString();
    jdbcTemplate.update(sql);
  }
}

package com.example.demo.repository.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.AI.entity.FaceSet;
import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.repository.FaceSetRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("faceSetRep")
public class FaceSetRepApl implements FaceSetRep {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private FaceHandler faceHandler;

  @Override
  public void creatTable() {
    jdbcTemplate.execute("use aiphoto");
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS faceset(\n").append("`faceStoreId` BIGINT NOT NULL PRIMARY KEY,\n").append("`cost` BIGINT NOT NULL,\n").append("`faceStoreName` VARCHAR(40) NOT NULL\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
  }

  @Override
  public FaceSet findById(Long id) {
    FaceSet faceSet = null;
    jdbcTemplate.execute("use aiphoto");
    faceSet = jdbcTemplate.queryForObject("select * from faceset where faceStoreId = ?",new BeanPropertyRowMapper<FaceSet>(FaceSet.class),id);
    return faceSet;
  }

  @Override
  public Long findExistById(Long Id) {
    jdbcTemplate.execute("use aiphoto");
    return jdbcTemplate.queryForObject("select count(*) from faceset where faceStoreId = ?",Long.class,Id);
  }

  /**
   * 网上申请人脸，然后返回添加人脸的人脸库ID，并记录在数据库中
   * @param faceSetName
   * @param faceSetDesc
   * @return
   */
  @Override
  public Long addFaceSet(String faceSetName, String faceSetDesc) {
    Long id = 0L;
    JSONObject jsonObject = faceHandler.createdFacesSet(faceSetName, faceSetDesc);
    FaceSet faceSet = JSON.toJavaObject(jsonObject, FaceSet.class);
    id = faceSet.getFaceStoreId();
    jdbcTemplate.execute("use aiphoto");
    jdbcTemplate.update("insert faceset(faceStoreId,faceStoreName,cost) values(?,?,?)",faceSet.getFaceStoreId(),faceSet.getFaceStoreName(),faceSet.getCost());
    return id;
  }

  /**
   * 删除本地和网络上的记录
   * @param faceStoreId
   * @return
   */
  @Override
  public boolean deleteFaceSetById(Long faceStoreId) {
    Long exist = findExistById(faceStoreId);
    if(exist!=0L){
      jdbcTemplate.execute("use aiphoto");
      jdbcTemplate.update("delete from faceset where faceStoreId = ? ", faceStoreId);
      faceHandler.deleteFaceSet(faceStoreId);
      return true;
    }
    return false;
  }
}

package com.example.demo.repository.impl;

import com.example.demo.entity._Base64Picture;
import com.example.demo.repository._Base64PicRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("b64PicRep")
public class _Base64PicRepApl implements _Base64PicRep {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Override
  public Long findExistById(Long Id) {
    return jdbcTemplate.queryForObject("select count(*) from b64picture_list where id = ?", Long.class, Id);
  }

  @Override
  public _Base64Picture findById(String username, Long Id) {
    _Base64Picture picture = null;
    picture = jdbcTemplate.queryForObject("select * from b64picture_list where id = ? and owner = ?",new BeanPropertyRowMapper<_Base64Picture>(_Base64Picture.class),Id,username);
    return picture;
  }

  @Override
  public _Base64Picture findById(Long Id) {
    _Base64Picture picture = null;
    picture = jdbcTemplate.queryForObject("select * from b64picture_list where id = ?",new BeanPropertyRowMapper<_Base64Picture>(_Base64Picture.class),Id);
    return picture;
  }

  @Override
  public Boolean addInfo(_Base64Picture pictrue) throws IOException {
    jdbcTemplate.update("insert b64picture_list(id,owner,categy,ispublic,picname,personTag,ishuman,b64,time) values(?,?,?,?,?,?,?,?,now())",
      pictrue.getId(),pictrue.getOwner(),pictrue.getCategy(), pictrue.getIspublic(),pictrue.getPicname(),pictrue.getPersontag(),pictrue.getIshuman(),pictrue.getB64());
    return true;
  }

  @Override
  public Boolean deleteById(Long Id) {
      return jdbcTemplate.update("delete from b64picture_list where id = ? ", Id )>0;
  }

  @Override
  public List<_Base64Picture> findList(String owner, boolean ispublic, String categy) {//第一分类向上兼容
      return jdbcTemplate.query("select * from b64picture_list where owner = ? and ispublic = ? and categy = ? order by time desc", new BeanPropertyRowMapper<_Base64Picture>(_Base64Picture.class), owner, ispublic, categy+"%");
  }

  @Override
  public List<_Base64Picture> getClassfiedfrPics(String owner, String categy) {//兼顾找所有照片中的分类。
    return jdbcTemplate.query("select * from b64picture_list where owner = ? and ispublic = false and categy = ? order by time desc", new BeanPropertyRowMapper<_Base64Picture>(_Base64Picture.class), owner, "_%"+categy);
  }

  @Override
  public void createTable() {
    //存储超长的文本
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS b64picture_list(\n").append("`id` BIGINT NOT NULL PRIMARY KEY,\n").append("`owner` VARCHAR(20) NOT NULL,\n").append("`categy` VARCHAR(20) NOT NULL,\n").append("`b64` MEDIUMTEXT NOT NULL,\n").append("`ispublic` BOOL DEFAULT TRUE,\n").append("`picname` VARCHAR(40) NOT NULL,\n").append("`persontag` VARCHAR(20) DEFAULT NULL,\n").append("`ishuman` BOOLEAN DEFAULT FALSE,\n").append("`time` date not null\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
  }

  @Override
  public Long findId() {
    Long exist = jdbcTemplate.queryForObject("select max(id) from b64picture_list",Long.class);
    if(exist == null)
      return 1L;
    else
      return exist+1L;
  }
}

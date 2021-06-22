package com.example.demo.repository.impl;

import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.entity.User;
import com.example.demo.entity.UserEx;
import com.example.demo.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository("userDao")
public class UserRepositoryApl implements UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private ImageClassficationRep imageClassficationRep;

  @Autowired
  private FaceSetRep faceSetRep;

  @Autowired
  private PictureRep pictureRep;

  @Autowired
  private _Base64PicRep _b64pictureRep;

  @Autowired
  private PicFaceRep picFaceRep;

  @Autowired
  private FaceRep faceRep;
  /**
   * 添加用户,
   * @param user 用户对象
   * @retur 返回是否添加成功
   */
  @Override
  public boolean insert(User user) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from tbl_user where username=?", Long.class, user.getUsername());
    if (exist != 0)
      return false;
    else {
      user.setFaceSet(faceSetRep.addFaceSet(user.getUsername()+"Public", user.getUsername()+" FaceSet"));
      // 在用户表创建一条用户记录
      // 在图像分类表创建一条用户的记录
      jdbcTemplate.update("insert tbl_user(username,password,truName,email,faceSet) values(?,?,?,?,?)", user.getUsername(), user.getPassword(), user.getTruName(), user.getEmail(),user.getFaceSet());
      jdbcTemplate.update("insert imageclassify(owner,ispublic) values(?,?)",user.getUsername(),false);
      return true;
    }
  }

  @Override
  public boolean insertEx(UserEx userEx) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from tbl_userEx where username=?", Long.class, userEx.getUsername());
    if (exist != 0)
      return false;
    else {
      jdbcTemplate.update("insert tbl_userEx(username,pripassword) values(?,?)", userEx.getUsername(),userEx.getPripassword());
      return true;
    }
  }

  @Override
  public boolean find(String name, String password) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from tbl_user where username=? and password=?", Long.class, name, password);
    if(exist==0){
      return false;
    }else{
      return true;
    }
  }

  @Override
  public boolean findEx(String name, String pripassword) {
    Long exist = jdbcTemplate.queryForObject("select count(*) from tbl_userEx where username=? and pripassword=?", Long.class, name, pripassword);
    if(exist==0){
      return false;
    }else{
      return true;
    }
  }

  @Override
  public void createTable() {
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS tbl_user(\n").append("`username` varchar(20) PRIMARY KEY,\n").append("`password` VARCHAR(20) NOT NULL,\n").append("`truName` VARCHAR(20) NOT NULL,\n").append("`email` varchar(30) not null,\n").append("faceSet Bigint default 0\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
    sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS `tbl_userEx` (\n").append("  `username` VARCHAR(20) NOT NULL,\n").append("  `pripassword` VARCHAR(40) NOT NULL,\n").append("  PRIMARY KEY (`username`)\n").append(") ENGINE=INNODB DEFAULT CHARSET=utf8").toString();
    jdbcTemplate.update(sql);
    Init();
  }

  @Override
  public User getUser(String username) {
    User user  = null;
    user = jdbcTemplate.queryForObject("select * from tbl_user where username=?",new BeanPropertyRowMapper<User>(User.class),username);
    return user;
  }

  @Override
  public void Init() {
    Long exist = jdbcTemplate.queryForObject("select count(*) from tbl_user where username=?", Long.class,"admin");
    if(exist == 0L){
//    初始化
      faceSetRep.creatTable();
      imageClassficationRep.createTable();
      _b64pictureRep.createTable();
      pictureRep.createTable();
      faceRep.createTable();
      picFaceRep.createTable();
//      添加根用户信息
      User adminuser = new User("admin","123456789","admin","1023668958@qq.com");
      insert(adminuser);
    }
  }
}

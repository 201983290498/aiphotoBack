package com.example.demo;

import com.example.demo.entity.PicFace;
import com.example.demo.repository.PicFaceRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class PicFaceTest {


  /**
   * 检测数据库的操作
   */

  @Autowired
  PicFaceRep picFaceRep;
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void createTableTest(){
    picFaceRep.createTable();
  }

  @Test
  void addInfo(){
    System.out.println(picFaceRep.add(new PicFace(1L, 1L)));
  }

  @Test
  void selectTest(){
    System.out.println(picFaceRep.findById(1L));
    System.out.println(picFaceRep.findExistById(2L));
    System.out.println(picFaceRep.findExistById(1L));
    System.out.println(picFaceRep.findExistById(1l, 1l));
    System.out.println(picFaceRep.findExistById(1l, 2l));
  }

  @Test
  void delete(){
    System.out.println(picFaceRep.deleteById(2L));
    int update = jdbcTemplate.update("delete from pic_face where id = ?", 1L);
    System.out.println(update);
  }

  @Test
  void findList(){

    System.out.println(picFaceRep.findByFace(2L).size());
    System.out.println(picFaceRep.findByPic(1L).size());
  }
}

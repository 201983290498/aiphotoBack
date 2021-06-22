package com.example.demo;


import com.example.demo.entity.Picture;
import com.example.demo.entity.User;
import com.example.demo.repository.ImageClassficationRep;
import com.example.demo.repository.PictureRep;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ImageClassifyService;
import com.example.demo.service.PictureService;
import com.example.demo.service.UserService;
import com.example.demo.tool.MyPictureIOS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class test {
  @Autowired
  private JdbcTemplate jdbcTemplate;


  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Test
  public void testJDBC(){
    jdbcTemplate.update("insert tbl_user(username,password,truName,email) values(?,?,?,?)","root","123456","cjm","123@qq.com");
    User query = jdbcTemplate.queryForObject("select * from tbl_user",new BeanPropertyRowMapper<User>(User.class));
    System.out.println(query);
  }

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public UserService userService;

  @Autowired
  public PictureRep pictureRep;

  @Autowired
  public PictureService pictureService;

  @Autowired
  public ImageClassficationRep imageClassficationRep;

  @Autowired
  public ImageClassifyService imageClassifyService;

  @Autowired
  public MyPictureIOS myPictureIOS;

  @Test
  public void testImageRepository(){
    List<String> root = imageClassficationRep.findPublicCategy();
    System.out.println(root);
    System.out.println(imageClassficationRep.deleteInfo("root", "others"));
    System.out.println(imageClassficationRep.deleteInfo("root","人物"));
    System.out.println(imageClassficationRep.addInfo("cjm","美食"));
    System.out.println(imageClassficationRep.addInfo("cjm","游戏"));
    System.out.println(imageClassficationRep.getCategy("cjm",false));
  }

  @Test
  public void testUser(){
    User user = new User("hjwhjw","123123","hjw","1023668958@qq.com");
    userService.register(user);
    User user1 = new User("cjm","121112","cjm","cjm@nuister.com");
    userService.register(user1);
    System.out.println(userService.check_in("cjm","121112"));
    System.out.println(userService.check_in("cjm","11111"));
  }

  @Test
  public void testPic() throws IOException, SQLException {
    Picture pic2 = new Picture("root","others",myPictureIOS.readImageBytes(myPictureIOS.path+"/root/1.jpg"),true,"1.jpg","0",false);
//    Picture pic1 = new Picture("root","others",null,true,"2.jpg","0",false);
//    System.out.println(pic1);
    pictureService._addPictureByBytes(pic2);
//    System.out.println(pictureRep.findById(1l));
    System.out.println(pictureService.getPublicPictureByCategy("cjm","others"));
  }

  @Test
  public void test(){
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS b64picture_list(\n").append("`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n").append("`owner` VARCHAR(20) NOT NULL,\n").append("`categy` VARCHAR(20) NOT NULL,\n").append("`b64` MEDIUMTEXT NOT NULL,\n").append("`ispublic` BOOL DEFAULT TRUE,\n").append("`picname` VARCHAR(40) NOT NULL,\n").append("`persontag` VARCHAR(20) DEFAULT NULL,\n").append("`ishuman` BOOLEAN DEFAULT FALSE\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
  }

}

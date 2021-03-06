package com.example.demo.repository.impl;



import com.example.demo.entity.ImageClassfication;
import com.example.demo.repository.ImageClassficationRep;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component("imgClassifyRep")
public class ImageClassficationRepApl implements ImageClassficationRep {

  @Autowired
  private JdbcTemplate jdbcTemplate;


  /**
   * 默认root是公共相册的分类，其他都是私人相册的分类
   * @param owner
   * @return
   */
  @Override
  public List<String> getCategy(String owner, boolean ispublic) {
    List<String> list = new LinkedList<>();
    jdbcTemplate.execute("use aiphoto");
    jdbcTemplate.execute("use aiphoto");
    List<ImageClassfication> query = jdbcTemplate.query("select * from imageclassify where owner=? and ispublic = ?", new BeanPropertyRowMapper<ImageClassfication>(ImageClassfication.class), owner,ispublic);
    for(ImageClassfication each : query){
      if(!each.getCategy().equalsIgnoreCase("others"))
        list.add(each.getCategy());
    }
    list.add("others");
    return list;
  }


  @Override
  public boolean deleteInfo(String owner, String categy) {
    if(!categy.equals("others")&&!owner.equals("root")) {
      jdbcTemplate.execute("use aiphoto");
      return jdbcTemplate.update("delete from imageclassify where owner=? and categy= ?", owner, categy)>0;
    }else
      return false;
  }

  @Override
  public boolean addInfo(String owner, String categy) {
    jdbcTemplate.execute("use aiphoto");
    Long exist = jdbcTemplate.queryForObject("select count(*) from imageclassify where owner=? and categy=?",Long.class,owner, categy);
    if(exist!=0)
      return false;
    else{
      jdbcTemplate.execute("use aiphoto");
        jdbcTemplate.update("insert imageclassify(owner,categy) value(?,?)",owner, categy);
      return true;
    }
  }

  /**
   * 添加新的分类
   * @param owner 拥有者
   * @param categy 分类明
   * @return 是否添加成功，无法重复添加
   */
  @Override
  public boolean addInfo(String owner, String categy,String remark) {
    jdbcTemplate.execute("use aiphoto");
    Long exist = jdbcTemplate.queryForObject("select count(*) from imageclassify where owner=? and categy=?",Long.class,owner, categy);
    if(exist!=0)
      return false;
    else{
      jdbcTemplate.execute("use aiphoto");
      if(remark.equals(""))
        jdbcTemplate.update("insert imageclassify(owner,categy) value(?,?)",owner, categy);
      else
        jdbcTemplate.update("insert imageclassify(owner,categy,remark) value(?,?,?)",owner, categy,remark);
      return true;
    }
  }

  @Override
  public boolean addInfo(String owner, String categy, boolean ispublic,String remark) {
    if(ispublic) {
      jdbcTemplate.execute("use aiphoto");
      Long exist = jdbcTemplate.queryForObject("select count(*) from imageclassify where owner=? and categy=?", Long.class, owner, categy);
      if (exist != 0)
        return false;
      else {
        jdbcTemplate.execute("use aiphoto");
        if(remark==null||remark.equals(""))
          jdbcTemplate.update("insert imageclassify(owner,categy,ispublic) value(?,?,?)", owner, categy,ispublic);
        else{
          jdbcTemplate.update("insert imageclassify(owner,categy,ispublic,remark) value(?,?,?,?)", owner, categy,ispublic,remark);
        }
        return true;
      }
    }else{
      return addInfo(owner,categy,remark);
    }
  }

  @Override
  public void createTable() {
    jdbcTemplate.execute("use aiphoto");
    String sql = new StringBuilder().append("CREATE TABLE IF NOT EXISTS imageClassify(\n").append("`id` INT AUTO_INCREMENT PRIMARY KEY,\n").append("`owner` VARCHAR(20) NOT NULL,\n").append("`categy` VARCHAR(20) NOT NULL DEFAULT 'others',\n").append("`ispublic` bool not null default false,\n").append("`remark`  varchar(300) default ''\n").append(")ENGINE=INNODB DEFAULT CHARSET=utf8;\n").toString();
    jdbcTemplate.update(sql);
//    System.out.println("create imageClassify========");
    initTable();
  }

  @Override
  public void initTable() {
    List<String> list = new LinkedList<String>();
    list.add("人物");list.add("交通工具");
    list.add("自然风景");list.add("建筑");
    list.add("动物");list.add("others");
//    System.out.println(list);
    for (int i = 0; i < list.size(); i++) {
      addInfo("admin", list.get(i),true,"");
    }
  }

  @Override
  public List<String> findPublicCategy() {
    return getCategy("admin",true);
  }
}

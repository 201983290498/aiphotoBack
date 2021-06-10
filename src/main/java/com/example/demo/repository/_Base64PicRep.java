package com.example.demo.repository;


import com.example.demo.entity._Base64Picture;
import java.io.IOException;
import java.util.List;

public interface _Base64PicRep {
  public static final String tableName = "picture_list";

  public Long findExistById(Long Id);

  /**
   * 按id查找图片
   * @param Id
   * @return
   */
  public _Base64Picture findById(String username,Long Id);
  public _Base64Picture findById(Long Id);

  public Boolean addInfo(_Base64Picture pictrue) throws IOException;

  public Boolean deleteById(Long Id);

  /**
   * 查找图片列表
   * @param owner
   * @param ispublic
   * @param categy
   * @return
   */
  public List<_Base64Picture> findList(String owner, boolean ispublic, String categy);

  public void createTable();

  Long findId();
}

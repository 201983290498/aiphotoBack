package com.example.demo.repository;

import com.example.demo.entity.Picture;

import java.io.IOException;
import java.util.List;

public interface PictureRep {
  public static final String tableName = "picture_list";

  public Long findExistById(Long Id);

  /**
   * 按id查找图片
   * @param Id
   * @return
   */
  public Picture findById(Long Id);

  /**
   * 从本地添加图片到数据库
   * 添加图片
   * @param picture
   */
  public Boolean addInfoLocal(Picture picture) throws IOException;

  public Boolean addInfoBybytes(Picture pictrue) throws IOException;

  public Boolean deleteById(Long Id);

  /**
   * 查找图片列表
   * @param owner
   * @param ispublic
   * @param categy
   * @return
   */
  public List<Picture> findList(String owner, boolean ispublic, String categy);

  public void createTable();
}

package com.example.demo.repository;


import com.example.demo.entity._Base64Picture;
import java.io.IOException;
import java.util.List;

public interface _Base64PicRep {
  String tableName = "picture_list";

  Long findExistById(Long Id);

  /**
   * 按id查找图片
   * @param Id 查找图片的ID
   */
  _Base64Picture findById(String username,Long Id);
  _Base64Picture findById(Long Id);

  Boolean addInfo(_Base64Picture pictrue) throws IOException;

  Boolean deleteById(Long Id);

  /**
   * 查找图片列表
   * @param owner 图片的拥有者
   * @param ispublic 图片的公私性
   * @param categy 分类
   * @return 图片不聚合的图片列表
   */
  List<_Base64Picture> findList(String owner, boolean ispublic, String categy);
  //新增获取图像分类之后的代码
  List<_Base64Picture> getClassfifyPics(String owner, boolean ispublic, String categy);
  void createTable();

  Long findId();

}
